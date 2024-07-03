package stepDefinition;

import Resources.GetTestData;
import Resources.SpecReqClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.GooglePlaceAPI.GoogleAddPlaceAPIRequest;
import pojo.GooglePlaceAPI.GoogleAddPlaceAPIResponse;
import pojo.GooglePlaceAPI.Location;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StepDefinition {
    RequestSpecification googleAddPlaceRequest;
    RequestSpecification req;
    ResponseSpecification res;
    String googleAddPlaceAPIResponse;
    @Given("Add place API payload")
    public void add_place_api_payload() {

        GetTestData addData = new GetTestData();
        GoogleAddPlaceAPIRequest googleAddPlaceAPIRequest = addData.addPlacePayload();

        req= SpecReqClass.reqSpecs();
        res = new ResponseSpecBuilder().expectStatusCode(200).build();

        googleAddPlaceRequest = given().spec(req).queryParam("key", "qaclick123").body(googleAddPlaceAPIRequest);
    }
    @When("User calls the add place API with post request")
    public void user_calls_the_add_place_api_with_post_request() {

        googleAddPlaceAPIResponse = googleAddPlaceRequest.when().post("place/add/json/")
                .then().extract().response().asString();
    }

    @Then("{string} should be {string}")
    public void shouldBe(String expectedKey, String expectedValue) {
        JsonPath jsonPath = new JsonPath(googleAddPlaceAPIResponse);
        Assert.assertEquals(jsonPath.getString(expectedKey), expectedValue);
    }


}
