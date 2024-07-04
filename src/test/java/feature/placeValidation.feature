Feature: Validate the Google place API's

  Scenario Outline: Validate the Add place API
    Given Add place API payload with "<name>" "<language>" "<address>"
    When User calls the add place API with post request
    Then "status" should be "OK"
    And "scope" should be "APP"
    Examples:
      |name |language|address  |
      |Suraj|Marathi |Sinner   |
      |Rohit|English |Chandwad |
      |Dipak|Hindi   |Kanadgaon|

