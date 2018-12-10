package autoriaapibyrestassured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class KeyAsObligatoryParam {

    public HelperMethods setupData;

    @BeforeMethod
    public void setup(){
        setupData = new HelperMethods();
        RestAssured.baseURI = setupData.getBaseUrl();
        }

    @Test
    public void checkResponceKeyAvailable() {
    given()
        .param("api_key", HelperMethods.API_KEY)
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
                .param("api_key", HelperMethods.API_KEY)
                .when().get("auto/search")
                .then().assertThat().statusCode(200);
    }






}
