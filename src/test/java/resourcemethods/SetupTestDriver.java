package resourcemethods;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetupTestDriver {

    public WebDriver driver ;
    public String browser ;
    public  String baseUrl ;
    private String os ;
    private String node;
/*
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
*/

    public SetupTestDriver(String os,String browser, String baseUrl, String node) throws MalformedURLException {
        this.browser = browser;
        this.os = os;
        this.baseUrl = baseUrl;
        this.node = node;
        Platform platform = Platform.fromString(os.toUpperCase());
        if(browser.equalsIgnoreCase("chrome")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), capability);
        } else if (browser.equalsIgnoreCase("firefox")) {
            DesiredCapabilities cap = DesiredCapabilities.firefox();
              cap.setBrowserName("firefox");
              cap.setPlatform(platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), cap);
        }

        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
      //  this.driver.get(baseUrl);

    }


    public String getBrowser() {
        return this.browser;
    }

    public String getBaseUrl() { return this.baseUrl; }

    public WebDriver getDriver() {
        return this.driver;
    }



}




