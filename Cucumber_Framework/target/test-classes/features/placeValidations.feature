Feature: Validating Place API's

 @AddPlaceAPI @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>","<language>","<place>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples: 	
|  name  | language |  place  |
|  Abdul | Urdu     | Pakistan|   
#| Sheikh | Islamic  | Dubai   |

 @DeletePlaceAPI @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "POST" http request
Then the API got success with status code 200
	And "status" in response body is "OK"