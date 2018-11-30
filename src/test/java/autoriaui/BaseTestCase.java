package autoriaui;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import resourcemethods.*;


import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTestCase {

    WebDriver driver;
    SetupTestDriver setup;
    AutoRiaComMainPage maipageobjects;
    SearchResultPage searchresultpage;
    baseresourceclass baseresources = new baseresourceclass();
    SaledCarPage saledcarpage;
    public String result;


    @BeforeTest(alwaysRun = true)
    @Parameters({"os","browser", "baseUrl", "node"})
    public void BaseTestCase(String os,String browser, String baseUrl, String node) throws MalformedURLException {

        setup = new SetupTestDriver(os, browser, baseUrl, node);
        driver = setup.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://auto.ria.com/");
        maipageobjects = new AutoRiaComMainPage(driver);
    }


    @Test (enabled = false)//Check default search criteria
    public void TU01() {
        // TODO moveto List to property file
        String[] assesrtlistOfDefaultSearchCriteria = {"true", "Легковые", "true", "null", "0", "0", "true", "true"};
        String[] ListOfDefaultSearchCriteria = maipageobjects.getListOfDefaultSearchCriteria();
        int i = 0;
        //
        for (String s : assesrtlistOfDefaultSearchCriteria) {
           // System.out.println(s + "  " + ListOfDefaultSearchCriteria[i]);
            Assert.assertEquals(ListOfDefaultSearchCriteria[i], s);
            i++;
        }
    }


    @Test (enabled = false) //Check ability to search with default search criteria
    public void TU02() {
        maipageobjects.searchButton.click();
        SearchResultPage searchresultpage = new SearchResultPage(driver);
        // TODO moveto List to property file
        String[] assesrtsearchCriteriaList = {"Легковые", "Растаможенные", "Авто в Украине"};
        String[] searchCriteriaListFromSearchResult = searchresultpage.getsearchCriteriaList();
        int i = 0;
        // search criteria list contains : "Легковые", "Растаможенные", "Авто в Украине"
        for (String s : assesrtsearchCriteriaList) {
            Assert.assertEquals(searchCriteriaListFromSearchResult[i], s);
            i++;
        }
    }

    @Test (enabled = false)//Check number of results per page with defult value - default 10 item per page expected
    public void TU03() {
        maipageobjects.searchButton.click();
        searchresultpage = new SearchResultPage(driver);
        //TODO how handle it test results multiple asserts
        Assert.assertEquals(searchresultpage.listOfItemsonSearchResultPage().size(), searchresultpage.getPaginationChangeSizeValue());
        Assert.assertTrue(driver.getWindowHandles().size()==1);
    }


    @Test (enabled = false) //Check ability to search with user defined search criteria
    public void TU04() {
        // TODO moveto List to property file
        // brand 13 - Chevrolet model - 1038 - Aveo
        String [] args = {"13","1038","Киев","1990", "2017", "2000", "40000"};
        maipageobjects.setSearchCriteria(args);
        maipageobjects.searchButton.click();
        searchresultpage = new SearchResultPage(driver);
        // TODO moveto List to property file
        String[] assesrtsearchCriteriaList = {"Легковые", "Audi", "100", "2012 - 2017 гг.", "Винницкая", "2000 - 4000 $", "Растаможенные", "Авто в Украине"};
        String[] searchCriteriaListFromSearchResult = searchresultpage.getsearchCriteriaList();
        int i=0;
        for (String s : searchCriteriaListFromSearchResult) {
            System.out.println(s);
            Assert.assertEquals(searchCriteriaListFromSearchResult[i], s);
            i++;
        }
    }


    @Test  //(enabled = false)//Check ability to open page of saled car from search list
    public void TU05() {
        // TODO moveto List to property file
        // brand 13 - Chevrolet model - 1038 - Aveo
        String [] args = {"13","1038","Киев","2000", "2018", "2000", "30000"};
        String brand = "Chevrolet";
        String model = "Aveo";
        maipageobjects.setSearchCriteria(args);
        maipageobjects.searchButton.click();
        searchresultpage = new SearchResultPage(driver);
        searchresultpage.listOfItemsonSearchResultPage().get(0).click();
        saledcarpage = new SaledCarPage(driver);
        Assert.assertEquals(brand,saledcarpage.getBrand());
        Assert.assertEquals(model,saledcarpage.getModel());
        Assert.assertTrue((Integer.parseInt(args[3])<= Integer.parseInt(saledcarpage.getYear()))&& (Integer.parseInt(saledcarpage.getYear()) <= Integer.parseInt(args[4])));
        Assert.assertTrue((Integer.parseInt(args[5])<= Integer.parseInt(saledcarpage.getPriceSeller()))&& (Integer.parseInt(saledcarpage.getPriceSeller()) <= Integer.parseInt(args[6])));
    }



    @AfterMethod
    public void afterMethod(ITestResult result) {
        baseresources.afterMethod(driver, result);
        driver.get(setup.getBaseUrl());
    }


    @AfterSuite
    public void close(){
        driver.close();

    }

}






