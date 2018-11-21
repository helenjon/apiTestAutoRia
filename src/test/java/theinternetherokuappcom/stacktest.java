package theinternetherokuappcom;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class stacktest {


    @Test
    public void teststackquestion() {

        String actual = "test";
        String expected = "run";
        Assert.assertEquals(actual, expected);

    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println(result.getStatus());
                System.out.println(result.getParameters());
                System.out.println(result.getMethod());
                System.out.println(result.isSuccess());
                //Do something here
                System.out.println("passed **********");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                //Do something here
                System.out.println(result.getStatus());
                System.out.println(result.getParameters());
                System.out.println(result.getMethod());
                System.out.println(result.isSuccess());
                System.out.println("Failed ***********");

            } else if (result.getStatus() == ITestResult.SKIP) {

                System.out.println("Skiped***********");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}