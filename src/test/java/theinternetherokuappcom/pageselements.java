package theinternetherokuappcom;

import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class pageselements {

/// http://the-internet.herokuapp.com/login   elements
    public WebElement getUserName(WebDriver driver){
        return driver.findElement(By.name("username"));
    }

    public WebElement getPassword(WebDriver driver){
        return driver.findElement(By.name("password"));
    }

    public WebElement getLogin(WebDriver driver){
        return driver.findElement(By.xpath(".//*[@class='fa fa-2x fa-sign-in']"));
    }

// http://the-internet.herokuapp.com/secure
    public WebElement getLogout(WebDriver driver){
    return driver.findElement(By.xpath(".//*[@href='/logout']"));
    }

//status_codes
    public ArrayList<String> listoflinks  (WebDriver driver){
        ArrayList<String> listofurls = new ArrayList<String>();
        List<WebElement> listoflinks = driver.findElements(By.xpath("//li/a"));
        for ( WebElement we: listoflinks ){
            System.out.println(we.getAttribute("href"));
            listofurls.add(we.getAttribute("href"));
            }
        return listofurls;
    }

//http://the-internet.herokuapp.com/javascript_alerts
  //  @FindBy(xpath = "//button[.='Click for JS Alert']")
  //  public WebElement getJSAlertbutton;

    public WebElement getJSAlertbutton(WebDriver driver){
    return driver.findElement(By.xpath("//button[.='Click for JS Alert']"));
    }

    public WebElement getJSConfirmButton(WebDriver driver){
        return driver.findElement(By.xpath("//button[.='Click for JS Confirm']"));
    }

    public WebElement getJSPromptButton(WebDriver driver){
        return driver.findElement(By.xpath("//button[.='Click for JS Prompt']"));
    }

    public WebElement getActionResult(WebDriver driver){
        return driver.findElement(By.id("result"));
    }


}

