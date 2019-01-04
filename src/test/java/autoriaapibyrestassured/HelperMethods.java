package autoriaapibyrestassured;

import dataprovider.ConfigFileReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class HelperMethods {

    public static String API_KEY ;
    public static String BASE_URL;
    private JsonPath jpOfSearchResult;
    private ConfigFileReader configFileReader;


    public HelperMethods() {
        configFileReader = new ConfigFileReader();
        this.API_KEY = configFileReader.getApiKey();
        this.BASE_URL = configFileReader.getBaseUrl();
        //this.API_KEY = "xmu8sNMjwhO29eIrRpTQMOG5KbFg6domccVFwzIZ";
        //this.BASE_URL = "https://developers.ria.com";
        RestAssured.baseURI = this.BASE_URL;

    }



// get search results for search with criteria
    public JsonPath runSearchWithCriteria(String[] args) {
        String searchResults = given()
                .param(args[0], args[1])
                .param(args[2], args[3])
                .param(args[4], args[5])
                .param("api_key", this.API_KEY)
                .when().get("auto/search").asString();
            // System.out.println(jsonSearchwithCriteria);
        jpOfSearchResult = new JsonPath(searchResults);
        return jpOfSearchResult;
    }

//get search results with default criteria
    public String runSearchWithNoCriteria() {
        String searchWithNoCriteria = given()
                .param("api_key", this.API_KEY)
                .when().get("auto/search").asString();
        return searchWithNoCriteria;

    }


    public JsonPath convertFromStringToJson(String json) {
        JsonPath jp = new JsonPath(json);
        return jp;
    }


    public String getAutoDataById(String auto_id) {
        String autoData = given()
                .param("auto_id", auto_id)
                .param("api_key", this.API_KEY)
                .when().get("auto/info").asString();
        return autoData;

    }

}


