package autoriaapibyrestassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ApiSearchAutoId {
    public HelperMethods setupData;

    @BeforeMethod
    public void setup() {
        setupData = new HelperMethods();
        RestAssured.baseURI = HelperMethods.BASE_URL;
    }


// check status code
    @Test
    public void checkStatusCodeforAutoId () {
        given()
                .param("auto_id", 23141405)
                .param("api_key", HelperMethods.API_KEY)
                .when().get("auto/info")
                .then().assertThat().statusCode(200);
    }


// check api search results complies to search criteria
    @Test
    public void checkSearchResults() {
        String [] args = {"category_id", "1","marka_id[0]", "79", "", ""};
        String [] argsToCheck = {"autoData.categoryId", "markId"};
        // run search with some criteria and get result as JSON
        JsonPath json = setupData.runSearchWithCriteria(args);
        //  transform html to Json and get list of IDs as result
        ArrayList <String> autoId = json.get("result.search_result.ids");
        // get  data about exact auto by auto_id
        String searchResult = setupData.getAutoDataById(autoId.get(0));
        // find data about first search criteria in exact auto data
        String firstSearchArg = setupData.convertFromStringToJson(searchResult).get(argsToCheck[0]).toString();
        // find data about second search criteria in exact auto data
        String secondSearchArg = setupData.convertFromStringToJson(searchResult).get(argsToCheck[1]).toString();
        Assert.assertEquals(firstSearchArg, args[1]);
        Assert.assertEquals(secondSearchArg, args[3]);

    }




}