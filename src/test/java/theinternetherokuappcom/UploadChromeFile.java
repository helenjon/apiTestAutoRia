package theinternetherokuappcom;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class UploadChromeFile {

    public static void uploadChromeFie(WebDriver driver, String uploadfilepath) throws InterruptedException, AWTException {

        // File Location
        StringSelection select = new StringSelection(uploadfilepath);
        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
        // Find the upload textbox
        driver.findElement(By.cssSelector("#file-upload")).click();
        // Create object of Robot class
        Robot robot = new Robot();
        Thread.sleep(1000);
        // Press CTRL+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        // Wait
      Thread.sleep(1000);
        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

}
