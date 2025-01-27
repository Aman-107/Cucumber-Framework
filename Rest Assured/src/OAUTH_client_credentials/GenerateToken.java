package OAUTH_client_credentials;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GenerateToken {

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
	
	
	given()
	.queryParam("access_token", token)
	
	.when()
	.get("oauthapi/getCourseDetails")
	
	.then().log().all()
	.assertThat().statusCode(401)
	.body("instructor",equalTo("RahulShetty"))
	;
	
		
	}

}
