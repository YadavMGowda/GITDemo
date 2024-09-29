Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload "<name>" "<language>" "<address>" "<phone_number>"
    When User Calls "AddPlaceAPI" with "POST" Http Request
    Then The API Call got Success with Status Code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify palce_Id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name          | language | address         | phone_number |
      | Arunya Nilaya | English  | Chalukya Nagara |   8884620989 |

  
  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given Delete Place Payload
    When User Calls "deletePlaceAPI" with "POST" Http Request
    Then The API Call got Success with Status Code 200
    And "status" in response body is "OK"