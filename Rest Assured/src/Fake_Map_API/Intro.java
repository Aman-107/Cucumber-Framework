package Fake_Map_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import files.payloads;
import static org.hamcrest.Matchers.*;

public class Intro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// given - All input details
		// when - Submit the API - resource,http method
		// then - Validate the response
		// manually import -> import static io.restassured.RestAssured.*;
		// manually import -> import static org.hamcrest.Matchers.*;  -> used to validate response body value json
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String updateAddress = "70 Summer walk, USA";
		String address = "Anupa Nivas";
		String name = "Aman Kumar Maurya";

		// validate if Add Place API is working as expected
	String response =	given().log().all()
		.queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payloads.addPlace(name,address))
		
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
//	System.out.println(place_id);
		
	//Add place -> Update Place with New Address -> Get Place to validate if New address is present in response
	
	// Get Place ID
	given().log().all()
	.queryParam("place_id", place_id).queryParam("key", "qaclick123")
	
	.when()
	.get("maps/api/place/get/json")
	
	.then().log().all()
	.assertThat().statusCode(200)
	.body("name", equalTo(name))
	;
	
	// Update Place ID 
	given().log().all()
	.queryParam("key", "qaclick123")
	.header("Content-Type","application/json")
	.body(payloads.updatePlace(place_id,updateAddress))
	
	.when()
	.put("maps/api/place/update/json")
	
	.then().log().all()
	.assertThat().statusCode(200)
	.body("msg", equalTo("Address successfully updated"))
	;
	
	// Get Place ID
		given().log().all()
		.queryParam("place_id", place_id).queryParam("key", "qaclick123")
		
		.when()
		.get("maps/api/place/get/json")
		
		.then().log().all()
		.assertThat().statusCode(200)
		.body("name", equalTo(name))
		.body("address", equalTo(updateAddress))
		;
	
	// Delete Place ID
	given().log().all()
	.queryParam("key", "qaclick123")
	.header("Content-Type","application/json")
	.body(payloads.deletePlace(place_id))
	
	.when()
	.delete("maps/api/place/delete/json")
	
	.then().log().all()
	.assertThat().statusCode(200)
	.body("status", equalTo("OK"))
	;
	
}
}
