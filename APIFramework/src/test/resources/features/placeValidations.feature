Feature: Validation Place API's

Scenario: Verify if Place is being successully added using ADDPlaceAPI
Given Add Place Payload
When user calls "AddPlaceAPI" with POST http request
Then the API call is successful with status code 200 
And "status" in response body is "OK"
And "scope" in response body is "APP"