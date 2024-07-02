package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
    @Given("Add place API payload")
    public void add_place_api_payload() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Given");
    }
    @When("User calls the add place API with post request")
    public void user_calls_the_add_place_api_with_post_request() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("When");

    }
    @And("The API call should be success with the error code")
    public void the_api_call_should_be_success_with_the_error_code() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("And");

    }
    @Then("Response should be OK")
    public void response_should_be_ok() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("then");

    }
}
