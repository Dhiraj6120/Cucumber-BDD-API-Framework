package stepDefinition;

import DataSet.APICalls;
import Resources.GetTestData;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.GooglePlaceAPI.GoogleAddPlaceAPIRequest;
import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils{
    RequestSpecification googleAddPlaceRequest;
    RequestSpecification googleGetAPIRequest;
    String googleDeletePlace;
    RequestSpecification googleDeleteRequest;
    String googleAddPlaceAPIResponse;
    String googleGetPlaceAPIResponse;
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
        } else if (method.equalsIgnoreCase("GET")) {
            googleGetPlaceAPIResponse = googleGetAPIRequest.when().get(APICalls.valueOf(apiCall).getApiCall())
                    .then().extract().response().asString();
        }
        else if (method.equalsIgnoreCase("Delete")){
            googleDeletePlace = googleDeleteRequest.when().delete(APICalls.deletePlaceAPI.getApiCall())
                    .then().extract().response().asString();
        }
    }
    @Then("{string} should be {string}")
    public void shouldBe(String expectedKey, String expectedValue) {
        Assert.assertEquals(stringToJson(googleAddPlaceAPIResponse, expectedKey), expectedValue);
    }

    @And("Verify the {string} in {string}")
    public void verifyTheIn(String name, String apiCall) {
        googleGetAPIRequest = given().spec(reqSpecs(name)).
                queryParam("key", "qaclick123").queryParam("place_id", stringToJson(googleAddPlaceAPIResponse, "place_id"));
        userCallsTheWithRequest(apiCall, "GET");
        String actualName = stringToJson(googleGetPlaceAPIResponse, "name");
        Assert.assertEquals(actualName, name);
        googleDeleteRequest = given().spec(reqSpecs(name)).queryParam("key", "qaclick123");
        userCallsTheWithRequest(apiCall, "Delete");
        shouldBe("status", "OK");
    }

}
