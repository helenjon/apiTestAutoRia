package autoriaapi;

import com.beust.jcommander.Parameter;
import com.sun.deploy.model.Resource;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;

public class testSEARCH {

    public baseResources resources = new baseResources();
    public testsearchmiddle tests = new testsearchmiddle();

    JSONObject myResponse;
    URLConnection myConnection;
    HttpURLConnection myHTMLdata;

    @BeforeTest
    public void gettestparams() {
        tests.resources.getrequstparamsfromfile("src/test/resources/test_data.csv");
        Reporter.log("MYTEST");
    }

    @AfterTest
    public void  afterMethod(){
       System.out.println(Reporter.getCurrentTestResult().getStatus());
        System.out.println(Reporter.getCurrentTestResult().getName());
        System.out.println(Reporter.getCurrentTestResult().isSuccess());
        System.out.println(Reporter.getCurrentTestResult().getHost());
        Reporter.log("MMMMMM");

    }


    @Test
    //check responce code
    public void responcecode200() throws MalformedURLException {
        Integer ResponseCode = tests.responcecode200();
        String expected = "200";
        Assert.assertEquals(ResponseCode.toString(), expected);
    }

    @Test
    public void categoryIdcheck() throws IOException {
         //get list of ID found cars
        JSONArray AutoIDs = tests.getdatafromJson();
        for (int i = 0; i < AutoIDs.length(); i++) {
            //get first ID from list
            String AutoID = AutoIDs.getString(i);
            //get data about found car
            String categoryId = tests.getdatabyAutoID(AutoID, "categoryId");
            Assert.assertEquals(categoryId, tests.resources.category);
        }

    }

    @Test
    public void markIDcheck() throws IOException {
        // get ID of found cars from search request
        tests.getdatafromJson();
        //get list of found cars
        JSONArray AutoIDs = tests.getdatafromJson();
        for (int i = 0; i < AutoIDs.length(); i++) {
            //get first ID from list
            String AutoID = AutoIDs.getString(i);
            //get data about found car
            String markID = tests.getdatabyAutoID(AutoID, "markId");
            Assert.assertEquals(markID, tests.resources.mark);
        }
    }

    @Test
    public void modelIdcheck() throws IOException {
        // get ID of found cars from search request
        JSONArray AutoIDs = tests.getdatafromJson();
        for (Object AutoID : AutoIDs) {
            String modelId = tests.getdatabyAutoID(AutoID.toString(), "modelId");
            Assert.assertEquals(modelId, tests.resources.model);
        }
    }

    @Test
    public void yearcheck() throws IOException {
        // get ID of found cars from search request
        tests.getdatafromJson();
        //get list of found cars
        JSONArray AutoIDs = tests.getdatafromJson();
        for (int i = 0; i < AutoIDs.length(); i++) {
            //get first ID from list
            String AutoID = AutoIDs.getString(i);
            //get data about found car
            String year = tests.getdatabyAutoID(AutoID, "year");
            Assert.assertTrue(Integer.valueOf(year) >= Integer.valueOf(tests.resources.year_from));
        }
    }


    /*
@Test
  public void testcase4() throws IOException {
        String searchrequest = resources.getsearchrequest();
        String responcedata = resources.getResponceData(searchrequest);
        //myResponse = resources.getJsonObject(responcedata);
        myResponse = resources.getJsonObject(resources.getResponceData(resources.getsearchrequest()));
        System.out.println("ids test 3" + myResponse.getJSONObject("result").getJSONObject("search_result").getJSONArray("ids"));
    }  */

}

