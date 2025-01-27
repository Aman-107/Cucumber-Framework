package POJO_Serialise;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class Map_API {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(ap)
		
		.when()
		.post("maps/api/place/add/json")
		
		.then().log().all()
		.assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		;
	}

}
