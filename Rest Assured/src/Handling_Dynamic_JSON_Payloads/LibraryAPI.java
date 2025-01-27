package Handling_Dynamic_JSON_Payloads;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class LibraryAPI {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166/Library";
		
	String response = given()
		.header("Content-Type","application/json")
		.body(payloads.addBook(isbn,aisle))
		
		.when()
		.post("Addbook.php")
		
		.then()
		.assertThat().statusCode(200)
		.extract().response().asString()
		;
	
	JsonPath js = new JsonPath(response);
	String id = js.get("ID");
	System.out.println(id);
	
	}
	
	@Test
	public void deleteBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166/Library";
		
		String response = given()
			.header("Content-Type","application/json")
			.body(payloads.addBook(isbn,aisle))
			
			.when()
			.post("Addbook.php")
			
			.then()
			.assertThat().statusCode(200)
			.extract().response().asString()
			;
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		
		// Parameterize the API Tests with multiple data sets
		return new Object[][] {{"abcd","1234"},{"xyzm","9876"},{"yuiop","6547"}};
		
		//HashMap <String,String> hs = new HashMap<String,String>();
	}
	
}
