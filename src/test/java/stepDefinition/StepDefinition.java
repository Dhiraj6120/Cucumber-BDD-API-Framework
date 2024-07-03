package stepDefinition;

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
        RestAssured.baseURI = "https://rahulshettyacademy.com/maps/api/";
        GoogleAddPlaceAPIRequest googleAddPlaceAPIRequest = new GoogleAddPlaceAPIRequest();
        Location location = new Location();
        location.setLat(41.87893233408357);
        location.setLng(-87.66676744998608);
        googleAddPlaceAPIRequest.setLocation(location);
        googleAddPlaceAPIRequest.setAccuracy(20);
        googleAddPlaceAPIRequest.setName("Shoes Shop");
        googleAddPlaceAPIRequest.setPhone_number("+1 5058188687");
        googleAddPlaceAPIRequest.setAddress("Ashland & Adams, Chicago, IL 60612, United States");
        googleAddPlaceAPIRequest.setLanguage("Eng-us");
        ArrayList<String> types = new ArrayList<>();
        types.add("asdnka");
        types.add("iqiwp");
        googleAddPlaceAPIRequest.setTypes(types);
        googleAddPlaceAPIRequest.setLanguage("En-Ind");

        req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/maps/api/")
                .setContentType(ContentType.JSON)
                .build();

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
