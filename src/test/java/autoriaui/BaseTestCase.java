package autoriaui;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import resourcemethods.*;




import java.util.concurrent.TimeUnit;

public class BaseTestCase {

    public WebDriver driver;
    public SetupTestDriver setup;
    public AutoRiaComMainPage maipageobjects;
    SearchResultPage searchresultpage;
    baseresourceclass baseresources = new baseresourceclass();
    public String result;


    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void BaseTestCase(String browser, String baseUrl) {

        setup = new SetupTestDriver(browser, baseUrl);
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


    @Test //Check ability to search with user defined search criteria
    public void TU04() {
        // TODO moveto List to property file
        String [] args = {"6","39","Винница","2012", "2017", "2000", "4000"};
        maipageobjects.setSearchCriteria(args);
        maipageobjects.searchButton.click();
        searchresultpage = new SearchResultPage(driver);
        // TODO moveto List to property file
        String[] assesrtsearchCriteriaList = {"Легковые", "Растаможенные", "Авто в Украине"};
        String[] searchCriteriaListFromSearchResult = searchresultpage.getsearchCriteriaList();
        int i=0;
        for (String s : searchCriteriaListFromSearchResult) {
            System.out.println(s);
            //Assert.assertEquals(searchCriteriaListFromSearchResult[i], s);
            i++;
        }

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






