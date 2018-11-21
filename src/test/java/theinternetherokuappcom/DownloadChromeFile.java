package theinternetherokuappcom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static groovy.xml.Entity.gt;


public class DownloadChromeFile {
    public static void main(String[] args) {
        String downloadFilepath = "c:\\download";

        Map<String, Object> chromePrefs = new HashMap <String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();

        Map <String, Object> chromeOntionsMap  = new HashMap <String, Object>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.linkText("IMG_2236.JPG")).click();
    }


}
