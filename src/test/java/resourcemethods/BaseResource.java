package resourcemethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.ITestResult;
import org.testng.Reporter;

public class BaseResource {


    public void afterMethod(WebDriver driver, ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                System.out.println("======PASS=====");
                // my expected functionality here when passed
                try {
                    TakeScreenShot.takeSnapShot(driver, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case ITestResult.FAILURE:
                System.out.println("======FAIL=====");

                // my expected functionality here when passed
                try {
                    TakeScreenShot.takeSnapShot(driver, result);
                    Reporter.log(TakeScreenShot.DestFileFailure);
                    //----------------------------------------
                    //TODO define better plase fo Reporter.log output  -- but where it should be ? ../apiTestAutoRia/test-output/old/All%20Test%20Suite/reporter-output.html
                    Reporter.log("screenshot saved at " + TakeScreenShot.DestFileFailure);
                    Reporter.log(result.getName());
                    Reporter.log("screenshot for " + result.getStatus(), true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case ITestResult.SKIP:
                System.out.println("======SKIP BLOCKED=====");
                // my expected functionality here when passed
                break;

            default:
                throw new RuntimeException("Invalid status");
        }

    }


    public ExpectedCondition <Boolean> waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        return pageLoadCondition;
    }

}