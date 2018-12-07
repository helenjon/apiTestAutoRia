package autoRiaApiByRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class apisearch_searchcriteriacheck {

public String api_key;
public HelperMethods setupdata;
public JsonPath resultjson;


@BeforeMethod
public void setupApi(){

        setupdata = new HelperMethods();
        RestAssured.baseURI = setupdata.getBaseUrl();
        api_key = setupdata.getApiKey();
    }

    @DataProvider(name = "dataforsearchcrteriacheck")
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                { "category_id", "1"},
                { "marka_id[0]", "79"},
                { "model_id[0]", "2"}
        };
    }

    @Test(dataProvider = "dataforsearchcrteriacheck")  //done
    public void checkStatusCodeWithSearchCriteria(String paramname, String paramvalue){
        given()
                .param(paramname, paramvalue)
                .param("api_key", api_key)
                .when().get("auto/search")
                .then().assertThat().statusCode(200);

}

    // check search was done by defined search criteria
    @Test //(enabled = false) //done
    public void searchCriteriaCheck(){
        //search criteria different variants
        //TODO  move to resourse file
        //String [] args = {"category_id", "1","marka_id[0]", "79", "", ""};
        //String [] args = {"category_id", "1", "marka_id[0]", "84", "model_id[0]", "31575"};
        String [] args = {"category_id", "1", "price_ot", "1000", "price_do", "60000"};
        //get result  of search with defined criteria as Json
        JsonPath json = setupdata.runSearchWithCriteria(args);
        // define path to fimd search criteria data
        String argsPath = "result.additional.search_params.all.";
        // check first search criteria
        Assert.assertEquals(json.get(argsPath+args[2]), args[3]);
        // check second search criteria
        Assert.assertEquals(json.get(argsPath+args[4]), args[5]);
 }









}
