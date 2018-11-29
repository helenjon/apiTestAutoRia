package autoriaui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SaledCarPage {

    private WebDriver driver;



    @FindBy(xpath = "//span[@itemprop = 'brand']")
    private WebElement brandsaledcar;

    @FindBy(xpath = "//span[@itemprop = 'name']")
    private WebElement modelsaledcar;

    @FindBy(css = ".price-seller>.price")
    private WebElement priceseller;

    @FindBy(css = ".heading > .head")
    private WebElement year;


    //Constructor
    public SaledCarPage (WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }


    public String getBrand(){
        return brandsaledcar.getAttribute("textContent");
    }


    public String getModel(){
        return modelsaledcar.getAttribute("textContent");
    }

    public String getPriceSeller(){
        String pureprise = priceseller.getAttribute("textContent");
        return pureprise.replaceAll("\\s","").substring(0,pureprise.length()-3);
    }

    public String getYear(){
        String[] data = year.getAttribute("textContent").split(" ");
        return data[data.length-1];
    }

}
