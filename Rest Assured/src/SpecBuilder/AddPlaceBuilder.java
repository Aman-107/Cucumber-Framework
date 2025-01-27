package SpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import POJO_Serialise.AddPlace;
import POJO_Serialise.Locations;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlaceBuilder {
	
	public static void main(String[] args) {
	
	AddPlace ap =new AddPlace();
	ap.setAccuracy(78);
	ap.setAddress("Anupa Nivas");
	ap.setLanguage("Hindi-IN");
	ap.setName("DarK ShadoW");
	ap.setPhone_number("(+91) 983 898 3837");
	ap.setWebsite("https://mvnrepository.com/");
	
	Locations loc = new Locations();
	loc.setLat(-38.383494);
	loc.setLng(33.427362);
	ap.setLocation(loc);
	
	List<String> type = new ArrayList<String>();
	type.add("Apna Ghar");
	type.add("Nizzi Ghar");
	ap.setTypes(type);
	
	RequestSpecification reqSpec = new RequestSpecBuilder()
	.setBaseUri("https://rahulshettyacademy.com")
	.addQueryParam("key", "qaclick123")
	.setContentType(ContentType.JSON).build();
	
	ResponseSpecification resSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
	
	RequestSpecification response = given().log().all().spec(reqSpec)
	.body(ap);
	
	String res2 = response.when()
	.post("maps/api/place/add/json")
	
	.then().log().all().spec(resSpec)
	.body("scope", equalTo("APP")).extract().asString()
	;
	
	System.out.println(res2);
	}
}
