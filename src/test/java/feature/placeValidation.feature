Feature: Validate the Google place API's

  Scenario: Validate the Add place API
    Given Add place API payload
    When User calls the add place API with post request
    Then The API call should be success with the error code
    And Response should be OK