package autoriaapibyrestassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchCriteriaCheck {

public HelperMethods setupData;
public JsonPath resultjson;


    @BeforeMethod
    public void setup(){
        setupData = new HelperMethods();
        RestAssured.baseURI = HelperMethods.BASE_URL;
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
    public void checkStatusCodeWithSearchCriteria(String paramName, String paramValue){
        given()
                .param(paramName, paramValue)
                .param("api_key", HelperMethods.API_KEY)
                .when().get("auto/search")
                .then().assertThat().statusCode(200);

}

    // check search was done by defined search criteria
    @Test //(enabled = false)
    public void checkSearchCriteria(){
        //search criteria different variants
        //TODO  move to resourse file
        //String [] args = {"category_id", "1","marka_id[0]", "79", "", ""};
        //String [] args = {"category_id", "1", "marka_id[0]", "84", "model_id[0]", "31575"};
        String [] args = {"category_id", "1", "price_ot", "1000", "price_do", "60000"};
        //get result  of search with defined criteria as Json
        JsonPath json = setupData.runSearchWithCriteria(args);
        // define path to find search criteria data
        String argsPath = "result.additional.search_params.all.";
        // check first search criteria
        Assert.assertEquals(json.get(argsPath+args[2]), args[3]);
        // check second search criteria
        Assert.assertEquals(json.get(argsPath+args[4]), args[5]);
 }





}
