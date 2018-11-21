package theinternetherokuappcom;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;

import javax.annotation.Nonnull;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class takescreenshot {

    public static String DestFileFailure;
    public static String DestFileSuccess;

    static void takeSnapShot(WebDriver webdriver, @Nonnull ITestResult result) throws Exception {
        //define data format
        DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        //get system data to file name
        Date data = new Date();
        String currentdata  = sdf.format(data);
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        //  File DestFile=new File(fileWithPath);
        //Copy file at destination
        String DestFile = "C:/My doc/Idea_projects/apiTestAutoRia/screenshots/";
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                DestFileFailure = DestFile + "failure/"+currentdata+".png";
              //  System.out.println(DestFileFailure);
                FileUtils.copyFile(SrcFile, new File(DestFileFailure));
            case ITestResult.SUCCESS:
                DestFileSuccess = DestFile + "passed/"+currentdata+".png";
                System.out.println(DestFileSuccess);
                FileUtils.copyFile(SrcFile, new File(DestFileSuccess));

        }
    }
}