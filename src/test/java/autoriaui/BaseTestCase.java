package autoriaui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import resourcemethods.*;


import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class BaseTestCase {

    WebDriver driver;
    SetupTestDriver setupTestDriver;
    AutoRiaComMainPage mainPageObjects;
    SearchResultPage searchResultPage;
    BaseResource baseResources = new BaseResource();
    SaledCarPage saledCarPage;
    WebDriverWait wait;
    public String result;


    @BeforeTest(alwaysRun = true)
    @Parameters({"os","browser", "baseUrl", "node"})
    public void BaseTestCase(String os,String browser, String baseUrl, String node) throws MalformedURLException {
        setupTestDriver = new SetupTestDriver(os, browser, baseUrl, node);
        driver = setupTestDriver.getDriver();
        driver.get("https://auto.ria.com/");
    // This part doen't work - thinking how to fix.
     /*   try {
            wait.until(baseResources.waitForLoad(driver));
        }
        catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }    */
        mainPageObjects = new AutoRiaComMainPage(driver);
    }

    //Check default search criteria
    @Test
    // is this good practise to name auto tests after number of testcases
    public void TU01() {
        // TODO move to List to property file   - how will be better to define those variables (from file? or create java class with variables?)
        String[] assesrtlistOfDefaultSearchCriteria = {"true", "Легковые", "true", "null", "0", "0", "true", "true"};
        String[] ListOfDefaultSearchCriteria = mainPageObjects.getListOfDefaultSearchCriteria();
        int i = 0;
        // TODO is this good to  handle asserts in such way?
        for (String s : assesrtlistOfDefaultSearchCriteria) {
            Assert.assertEquals(ListOfDefaultSearchCriteria[i], s);
            i++;
        }
    }

    //Check ability to search with default search criteria
    @Test
    public void TU02() {
        mainPageObjects.searchButton.click();
        SearchResultPage searchresultpage = new SearchResultPage(driver);
        // TODO move variables to property file ?
        String[] assesrtsearchCriteriaList = {"Легковые", "Растаможенные", "Авто в Украине"};
        String[] searchCriteriaListFromSearchResult = searchresultpage.getsearchCriteriaList();
        int i = 0;
        // search criteria list contains : "Легковые", "Растаможенные", "Авто в Украине"
        //!!!!!
        for (String s : assesrtsearchCriteriaList) {
            Assert.assertEquals(searchCriteriaListFromSearchResult[i], s);
            i++;
        }
    }
    //Check number of results per page with defult value - default 10 item per page expected (by docs)
    @Test
    public void TU03() {
        mainPageObjects.searchButton.click();
        searchResultPage = new SearchResultPage(driver);
        //TODO how handle it test results multiple asserts  ?
        Assert.assertEquals(searchResultPage.listOfItemsonSearchResultPage().size(), searchResultPage.getPaginationChangeSizeValue());
        Assert.assertTrue(driver.getWindowHandles().size()==1);
    }

    //Check ability to search with user defined search criteria
    @Test
    public void TU04() {
        // TODO moveto List to property file
        // brand 13 - Chevrolet model - 1038 - Aveo
        String [] args = {"13","1038","Киев","1990", "2017", "2000", "40000"};
        mainPageObjects.setSearchCriteria(args);
        mainPageObjects.searchButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchResultPage = new SearchResultPage(driver);
       // TODO moveto List to property file
        String[] assesrtsearchCriteriaList = {"Легковые", "Audi", "100", "2012 - 2017 гг.", "Винницкая", "2000 - 4000 $", "Растаможенные", "Авто в Украине"};
        String[] searchCriteriaListFromSearchResult = searchResultPage.getsearchCriteriaList();
        int i=0;
        for (String s : searchCriteriaListFromSearchResult) {
            System.out.println(s);
            Assert.assertEquals(searchCriteriaListFromSearchResult[i], s);
            i++;
        }
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    //Check ability to open page of saled car from search list
    @Test
    public void TU05() {
        // TODO moveto List to property file
        // brand 13 - Chevrolet model - 1038 - Aveo
        String [] args = {"13","1038","Киев","2000", "2018", "2000", "30000"};
        String brand = "Nissan";
        String model = "Juke";
        mainPageObjects.setSearchCriteria(args);
        // run search by criteria
        mainPageObjects.searchButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchResultPage = new SearchResultPage(driver);
        searchResultPage.listOfItemsonSearchResultPage().get(0).click();
        saledCarPage = new SaledCarPage(driver);
        Assert.assertEquals(brand,saledCarPage.getBrand());
        Assert.assertEquals(model,saledCarPage.getModel());
        // this assert check whether  year of found car correspond to search criteria interval for year
        Assert.assertTrue((Integer.parseInt(args[3])<= Integer.parseInt(saledCarPage.getYear()))&& (Integer.parseInt(saledCarPage.getYear()) <= Integer.parseInt(args[4])));
        // this assert check whether  price of found car correspond to search criteria interval for price
        Assert.assertTrue((Integer.parseInt(args[5])<= Integer.parseInt(saledCarPage.getPriceSeller()))&& (Integer.parseInt(saledCarPage.getPriceSeller()) <= Integer.parseInt(args[6])));
    }



    @AfterMethod
    public void afterMethod(ITestResult result)
    {
        baseResources.afterMethod(driver, result);
        driver.get(setupTestDriver.getBaseUrl());
    }

// no idea how to handle error
    @AfterTest
    public void close(){
        driver.quit();
        driver.close();

    }

}






