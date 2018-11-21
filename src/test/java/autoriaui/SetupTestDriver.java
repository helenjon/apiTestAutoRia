package autoriaui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SetupTestDriver {

    public WebDriver driver ;
    public String browser ;
    public  String baseUrl ;

    public SetupTestDriver(String browser, String baseUrl) {

        this.browser = browser;
        this.baseUrl = baseUrl;

        if(browser.equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            this.driver = new FirefoxDriver();
        }
        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }


    public String getBrowser() {
        return this.browser;
    }

    public String getBaseUrl() { return this.baseUrl; }

    public WebDriver getDriver() {
        return this.driver;
    }



}




