Feature: Validate the Google place API's

  Scenario Outline: Validate the Add place API
    Given Add place API payload with "<name>" "<language>" "<address>"
    When User calls the "addPlaceAPI" with "post" request
    Then "status" should be "OK"
    And "scope" should be "APP"
    And Verify the "<name>" in "getPlaceAPI"
#    And User calls the "deletePlaceAPI" with "delete" request
#    Then "status" should be "OK"
    Examples:
      |name |language|address  |
      |Dhokala Center|Marathi |Sinner   |
#      |McDonalds|English |Chandwad |
#      |Burger king|Hindi   |Kanadgaon|

  Scenario: Verify user able to delete place
    Given User has Delete API Payload
    When User calls the "deletePlaceAPI" with "delete" request
    Then "status" should be "OK"

