package autoRiaApiByRestAssured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class apisearh_keycheck {

    public String api_key;
    public HelperMethods setupdata;

    @BeforeMethod
public void setupApi(){
    setupdata = new HelperMethods();
    RestAssured.baseURI = setupdata.getBaseUrl();
    api_key = setupdata.getApiKey();
    }

    @Test
    public void checkResponceKeyAvailable() {
    given()
        .param("api_key", api_key)
    .when().get("auto/search")
    .then().assertThat().statusCode(200);
}

    @Test
    public void checkResponceKeyNotAvailable() {
    given()
            .when().get("auto/search")
            .then().assertThat().statusCode(403);
}

    @Test
    public void checkResponceSearchCriteriaKeyNotAvailable() {
    given()
            .param("category", "1")
            .param("marka_id[0]", "4")
    .when().get("auto/search")
    .then().assertThat().statusCode(403);
}

    @Test //(enabled = false)
    public void checkResponceKeyAvailableWrongParamOrder() {
        given()
                .param("category", "1")
                .param("api_key", api_key)
                .when().get("auto/search")
                .then().assertThat().statusCode(200);
    }






}
