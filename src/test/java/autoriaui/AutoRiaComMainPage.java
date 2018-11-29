package autoriaui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AutoRiaComMainPage {

    private WebDriver driver;
    private String pageurl = "https://auto.ria.com/";
    private List<String> setOfSearchCriteria = new ArrayList<String>();


    //Constructor
    public AutoRiaComMainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(pageurl);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Apply found radio button BU auto
    @FindBy(css = "#buRadioType")
    public WebElement buautoradiotype;


    //Apply found list of categories
    @FindBy(css = "#categories > option[selected]")
    public WebElement categories;

    //Apply found list of brand
    @FindBy(css = "#brandTooltipBrandAutocompleteInput-brand")
    public WebElement brand;

    //Apply found list of model
    @FindBy(css = "#brandTooltipBrandAutocomplete-model")
    public WebElement model;

    //Apply found list of region
    @FindBy(css = "#brandTooltipBrandAutocomplete-region")
    public WebElement region;


    //Apply found yearFrom
    @FindBy(css = "#yearFrom")
    public WebElement yearFrom;


    //Apply found yearTo
    @FindBy(css = "#yearTo")
    public WebElement yearTo;


    //Apply found yearTo
    @FindBy(css = "#priceFrom")
    public WebElement priceFrom;

    //Apply found yearTo
    @FindBy(css = "#priceTo")
    public WebElement priceTo;

    //
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement searchButton;


    public void setBrand(String valuetoselect){
    // TODO moveto List to property file // 6 - AUDI
    //String valuetoselect = "6";
    driver.findElement(By.cssSelector("#brandTooltipBrandAutocomplete-region")).click();
    //brand.click();
    selectFromDropdownList(valuetoselect);
    }

    public void setModel(String valuetoselect){
        // TODO moveto List to property file // 39 - 100
        //String valuetoselect = "39";
        model.click();
        selectFromDropdownList(valuetoselect);
    }

    public void setRegion(String valuetoselect){
        region.click();
        List<WebElement> options = driver.findElements(By.xpath("//ul[@class= 'unstyle scrollbar autocomplete-select']/li/a"));
        for (WebElement option : options) {
            if (valuetoselect.equals(option.getAttribute("textContent"))) {
                option.click();
                break;
            }
        }
    }

    public void setYearFrom(String yearFromvalue) {
        Select dropdown = new Select(driver.findElement(By.id("yearFrom")));
        dropdown.selectByValue(yearFromvalue);
    }

    public void setYearTo(String yearTovalue){
        Select dropdown = new Select(driver.findElement(By.id("yearTo")));
        dropdown.selectByValue(yearTovalue);
    }

    public void setPriceFrom(String PriceFrom){
        driver.findElement(By.cssSelector("#priceFrom")).sendKeys(PriceFrom);
    }

    public void setPriceTo(String PriceTo){
        driver.findElement(By.cssSelector("#priceTo")).sendKeys(PriceTo);
    }


    void selectFromDropdownList(String valuetoselect){
         List<WebElement> options = driver.findElements(By.xpath("//ul[@class= 'unstyle scrollbar autocomplete-select']/li"));
         for (WebElement option : options) {
             if (valuetoselect.equals(option.getAttribute("data-value"))) {
                 option.click();
                 break; }
         }
     }


    public void setSearchCriteria(String[] args){
        setBrand(args[0]);
        setModel(args[1]);
        setRegion(args[2]);
        setYearFrom(args[3]);
        setYearTo(args[4]);
        setPriceFrom(args[5]);
        setPriceTo(args[6]);
    }



    //Get name of selected category
    public String getSelectedCategory() {
        return categories.getText();
    }

    public String getSelectedBrand() {
        return brand.getAttribute("value");
    }

    public String getSelectedModel() {
        return model.getAttribute("value");
    }

    public String getSelectedRegion() {
        if (region.getAttribute("value") == null) {
        return "null";}
        return region.getAttribute("value");
    }

    public String getSelectedYearFrom() {
        return yearFrom.getAttribute("value");
    }

    public String getSelectedYearTo() {
        return yearTo.getAttribute("value");
    }

    public String getSelectedPriceFrom() {
        return priceFrom.getAttribute("value");
    }

    public String getSelectedPriceTo() {
        return priceTo.getAttribute("value");
    }

    //to check defaut sarch criteria
    public String[] getListOfDefaultSearchCriteria(){

        setOfSearchCriteria.add(buautoradiotype.getAttribute("checked"));
        setOfSearchCriteria.add(getSelectedCategory());
        setOfSearchCriteria.add(Boolean.toString(getSelectedBrand().isEmpty()));
        setOfSearchCriteria.add(getSelectedRegion());
        setOfSearchCriteria.add(getSelectedYearFrom());
        setOfSearchCriteria.add(getSelectedYearTo());
        setOfSearchCriteria.add(Boolean.toString(getSelectedPriceFrom().isEmpty()));
        setOfSearchCriteria.add(Boolean.toString(getSelectedPriceTo().isEmpty()));
        return setOfSearchCriteria.toArray(new String[setOfSearchCriteria.size()]);

    }


    // return array of search criteria from main page
    public String[] getListOfSearchCriteria(){
        setOfSearchCriteria.add(getSelectedCategory());
        setOfSearchCriteria.add(getSelectedBrand());
        setOfSearchCriteria.add(getSelectedModel());
        setOfSearchCriteria.add(getSelectedRegion());
        setOfSearchCriteria.add(getSelectedYearFrom());
        setOfSearchCriteria.add(getSelectedYearTo());
        setOfSearchCriteria.add(getSelectedPriceFrom());
        setOfSearchCriteria.add(getSelectedPriceTo());
        //Convert to string array
        String[] array = setOfSearchCriteria.toArray(new String[setOfSearchCriteria.size()]);
        return setOfSearchCriteria.toArray(new String[setOfSearchCriteria.size()]);
    }







}