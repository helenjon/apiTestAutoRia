package autoriaui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {

    private WebDriver driver;
    private List<String> listOfSearchCriteria = new ArrayList<String>();




    //Constructor
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Found list search criteria objects
    @FindBys({@FindBy(css = ".tagging-filter.small > .item")})
    private List<WebElement> searchCriteriaObjects;


    @FindBys({@FindBy(css = ".item.ticket-title")})
    private List<WebElement> listOfItemsonSearchResultPage;

    public List<WebElement> listOfItemsonSearchResultPage(){
        return listOfItemsonSearchResultPage;
    }

    @FindBy(css = "#paginationChangeSize")
    private WebElement paginationChangeSize;


    public String[] getsearchCriteriaList (){
        for (WebElement obj :  searchCriteriaObjects) {
            listOfSearchCriteria.add(obj.getAttribute("textContent"));
        }
        return listOfSearchCriteria.toArray(new String[listOfSearchCriteria.size()]);
    }

    public String getPaginationChangeSizeValue(){
        return paginationChangeSize.getAttribute("textContent").substring(0,2);
    }

}
