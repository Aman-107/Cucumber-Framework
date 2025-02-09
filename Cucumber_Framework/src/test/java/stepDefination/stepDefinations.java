package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resources.TestDataBuild;
import Resources.Utils;
import Resources.WhenReources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinations extends Utils {
	
	RequestSpecification req;
	Response response;
	ResponseSpecification resspec;
	static String place_id; // static keyword is used bcoz after every scenario executed it will point out to null hence used static which will store it's value
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string},{string},{string}")
	public void add_place_payload_with(String name, String language, String place) throws IOException {
		
		req = given()
		.spec(requestSpecification())
		.body(data.addPlacePayload(name,language,place));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apiName, String method) {
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
		// contructor will be called with the value of resource which you have pass	
		WhenReources when = WhenReources.valueOf(apiName); // Enum
		System.out.println(when.getResource());
		
		if(method.equalsIgnoreCase("post")) {
		 response = req.when().post(when.getResource()); // passing enum extracted value
		}
		else if (method.equalsIgnoreCase("get")) {
			response = req.when().get(when.getResource());
		}
			
//		.then()
//		.spec(resspec).extract().response();
	}
	
	@Then("the API got success with status code {int}")
	public void the_api_got_success_with_status_code(Integer int1) {
	   
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	   
	//  String resp =  response.asString();
	  assertEquals(getJsonPath(response,string),string2);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String apiName) throws IOException {
	   
		// request spec -> 
		place_id = getJsonPath(response,"place_id");
		req = given()
			 .spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(apiName,"GET");
		assertEquals(getJsonPath(response,"name"),name);	
	}
	

@Given("DeletePlace Payload")
public void delete_place_payload() throws IOException {
  
	req = given()
			.spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	
}

}


/* If there is any issue while running the code make sure to delete 'JUNIT 4' from 'classpath'.
 * 
 */
