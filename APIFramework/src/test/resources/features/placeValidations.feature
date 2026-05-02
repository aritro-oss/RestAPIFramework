Feature: Validation Place API's

@AddPlace
Scenario Outline: Verify if Place is being successully added using ADDPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST"  http request
Then the API call is successful with status code 200 
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
|name   |language|address            |
|AAhouse|English |World cross Center |
#|BBhouse|Spanish |Park Street        |

@DeletePlace
Scenario: Verify if Delete Place functionality is working
Given Deleteplace Payload
When user calls "deletePlaceAPI" with "POST"  http request
Then the API call got success with status code 200
And "status" in response body is "OK"
