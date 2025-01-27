package Handling_Dynamic_JSON_Payloads;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BodyFromExternalJson {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// validate if Add Place API is working as expected
	String response =	given().log().all()
		.queryParam("key", "qaclick123").header("Content-Type","application/json")
	// content of the file to String -> content of file can convert into Byte -> Byte data to String
		.body(new String(Files.readAllBytes(Paths.get("E://SDET Codes Ecllipse//addPlace.json"))))
		
		.when()
		.post("maps/api/place/add/json")
		
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString()
		;
	
	// Parsing json to string
	JsonPath js = new JsonPath(response);
	String place_id = js.getString("place_id");
			
	}

}
