package autoRiaApiByRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class apisearch_autoid {
    public String api_key;
    public HelperMethods setupdata;

    @BeforeMethod
    public void setupApi() {
        setupdata = new HelperMethods();
        RestAssured.baseURI = setupdata.getBaseUrl();
        api_key = setupdata.getApiKey();
    }


// check status code
    @Test
    public void checkStatusCodeforAutoId () {
        given()
                .param("auto_id", 23141405)
                .param("api_key", api_key)
                .when().get("auto/info")
                .then().assertThat().statusCode(200);
    }


// check api search results complain to search criteria
    @Test
    public void checkSearchResults() {
        String [] args = {"category_id", "1","marka_id[0]", "79", "", ""};
        String [] argsToCheck = {"autoData.categoryId", "markId"};
        // run search with some criteria and get result as JSOn
        JsonPath json = setupdata.runSearchWithCriteria(args);
        //  transform html to Json and get List of IDs as result
        ArrayList <String> auto_id = json.get("result.search_result.ids");
        // get exact data about auto by auto_id
        String searchresult = setupdata.autoidfined(auto_id.get(0));
        // find data about first search criteria in exact auto data
        String firstsearcharg = setupdata.fromStringToJson(searchresult).get(argsToCheck[0]).toString();
        // find data about second search criteria in exact auto data
        String secondsearcharg = setupdata.fromStringToJson(searchresult).get(argsToCheck[1]).toString();
        Assert.assertEquals(firstsearcharg, args[1]);
        Assert.assertEquals(secondsearcharg, args[3]);

    }




}