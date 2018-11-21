package resourcemethods;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

public class baseresourceclass {


    public void afterMethod(WebDriver driver, ITestResult result)   {
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                System.out.println("======PASS=====");
                // my expected functionality here when passed
                try {
                    takescreenshot.takeSnapShot(driver, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case ITestResult.FAILURE:
                System.out.println("======FAIL=====");

                // my expected functionality here when passed
                try {
                    takescreenshot.takeSnapShot(driver, result);
                    Reporter.log(takescreenshot.DestFileFailure);
                    //----------------------------------------
                    //TODO define better plase fo Reporter.log output - currently it's - /C:/My%20doc/Idea_projects/apiTestAutoRia/test-output/old/All%20Test%20Suite/reporter-output.html
                    Reporter.log("screenshot saved at " + takescreenshot.DestFileFailure);
                    Reporter.log(result.getName() );
                    Reporter.log("screenshot for "+ result.getStatus(), true);

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

}
