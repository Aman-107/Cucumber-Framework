package POJO_Classes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Calling_POJO_ {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
		String response = given().log().all()
			.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParam("grant_type", "client_credentials")
			.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParam("scope", "trust")
			
			.when()
			.post("/oauthapi/oauth2/resourceOwner/token")
			
			.then().log().all()
			.assertThat().statusCode(200)
			.body("scope", equalTo("create"))
			.extract().response().asString()
			;
		
		JsonPath js = new JsonPath(response);
		String token = js.get("access_token");
		
		
		GetCourse gc = given()
		.queryParam("access_token", token)
		
		.when()      // Pojo will be called at '.when()' function only it can't be called at the '.then()' method
		.get("oauthapi/getCourseDetails").as(GetCourse.class);
		
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		
		List<Api> api = gc.getCourses().getApi();
		for(int i=0; i<api.size(); i++) {
			if(api.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
				System.out.println(api.get(i).getCourseTitle() + " " + api.get(i).getPrice());
			}
		}
		
		String [] courseTitlesWA = {"Selenium Webdriver Java","Cypress","Protractor"};
		ArrayList<String> arr = new ArrayList<String>();
 		List<WebAutomation> webAutomation = gc.getCourses().getWebAutomation();
 		
		for(int i=0; i<webAutomation.size(); i++) {
		arr.add(webAutomation.get(i).getCourseTitle());
	}
	
		List<String> expectedList = Arrays.asList(courseTitlesWA);
		Assert.assertTrue(arr.equals(expectedList));
		
	}

} 
