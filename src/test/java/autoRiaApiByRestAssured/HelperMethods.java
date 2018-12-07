package autoRiaApiByRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class HelperMethods {

    public String api_key;
    public String baseUrl;
    private JsonPath jpOfSearchResult;


    public HelperMethods() {
        this.api_key = "xmu8sNMjwhO29eIrRpTQMOG5KbFg6domccVFwzIZ";
        this.baseUrl = "https://developers.ria.com";
        RestAssured.baseURI = this.baseUrl;



    }

    public String getApiKey() {
        return this.api_key;

    }

    public String getBaseUrl() {
        return this.baseUrl;

    }

// get search results for search with criteria
    public JsonPath runSearchWithCriteria(String[] args) {
        String searchresults = given()
                .param(args[0], args[1])
                .param(args[2], args[3])
                .param(args[4], args[5])
                .param("api_key", this.api_key)
                .when().get("auto/search").asString();
            // System.out.println(jsonSearchwithCriteria);
        jpOfSearchResult = new JsonPath(searchresults);
        return jpOfSearchResult;
    }

    public String runSearchWithNoCriteria() {
        String jsonSearchwithCriteria = given()
                .param("api_key", this.api_key)
                .when().get("auto/search").asString();
        return jsonSearchwithCriteria;

    }


    public JsonPath fromStringToJson(String json) {
        JsonPath jp = new JsonPath(json);
        return jp;
    }


    public String autoidfined(String auto_id) {
        String jsonauto_idSearch = given()
                .param("auto_id", auto_id)
                .param("api_key", this.api_key)
                .when().get("auto/info").asString();
        return jsonauto_idSearch;

    }

}


