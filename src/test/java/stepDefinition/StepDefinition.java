package stepDefinition;

import DataSet.APICalls;
import Resources.GetTestData;
import Resources.Utils;
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

    @Then("{string} should be {string}")
    public void shouldBe(String expectedKey, String expectedValue) {
        JsonPath jsonPath = new JsonPath(googleAddPlaceAPIResponse);
        Assert.assertEquals(jsonPath.getString(expectedKey), expectedValue);
    }

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
}
