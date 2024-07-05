package stepDefinition;

import DataSet.APICalls;
import Resources.GetTestData;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.GooglePlaceAPI.GoogleAddPlaceAPIRequest;
import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils{
    RequestSpecification googleAddPlaceRequest;
    String googleAddPlaceAPIResponse;
    String responseGetPlaceAPI;
    @Given("Add place API payload with {string} {string} {string}")
    public void addPlaceAPIPayloadWith(String name, String language, String address) {
        GetTestData addData = new GetTestData();
        GoogleAddPlaceAPIRequest googleAddPlaceAPIRequest = addData.addPlacePayload(name, language, address);
        googleAddPlaceRequest = given().spec(reqSpecs(name)).queryParam("key", "qaclick123").body(googleAddPlaceAPIRequest);
    }
    @When("User calls the {string} with {string} request")
    public void userCallsTheWithRequest(String apiCall, String method) {
        if (method.equalsIgnoreCase("POST")){
            googleAddPlaceAPIResponse = googleAddPlaceRequest.when().post(APICalls.valueOf(apiCall).getApiCall())
                    .then().extract().response().asString();
        } else if (method.equalsIgnoreCase("get")) {
            googleAddPlaceAPIResponse = googleAddPlaceRequest.when().get(APICalls.valueOf(apiCall).getApiCall())
                    .then().extract().response().asString();
        }
    }
    @Then("{string} should be {string}")
    public void shouldBe(String expectedKey, String expectedValue) {
        Assert.assertEquals(stringToJson(googleAddPlaceAPIResponse, expectedKey), expectedValue);
    }

    @And("Verify the {string} in {string}")
    public void verifyTheIn(String name, String apiCall) {
        RequestSpecification getAPIReq = given().spec(reqSpecs(name)).
                queryParam("key", "qaclick123").queryParam("place_id", stringToJson(googleAddPlaceAPIResponse, "place_id"));

        responseGetPlaceAPI = getAPIReq.get(APICalls.valueOf(apiCall).getApiCall())
                .then().extract().response().asString();
        String actualName = stringToJson(responseGetPlaceAPI, "name");
        Assert.assertEquals(actualName, name);
    }
}
