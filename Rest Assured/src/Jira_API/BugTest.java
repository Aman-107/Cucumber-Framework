package Jira_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payloads;

public class BugTest {

	String id;
	
	@Test(dataProvider = "JiraInfo")
	public void createBug(HashMap<String,String> hs) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://akthemaurya1999-1737183789949.atlassian.net";
		
	String response =	given()
		.header("Authorization","Basic YWt0aGVtYXVyeWExOTk5QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBuOFN5X3NLeVUzbUh6Sm5uZzBRTW1rbGxRLTlPOFNFVE55cW5Lbkk3VGJBZWtfWEJCbHlqU3Y5S2p2NnBPVUJQbVVyWWgyczRBNVZBTHg1Zy1oTDU3Z3NMVVJKS0ZWTjhsTEUzZGpjSEN1TWtQM1BFZEV4cEh3emZBLVExLVFDTXB5cTgyNFVkWHRzRk5NRUNQMGpSX2FWaGZUNnM1ZlNYeHQ0cm52c2p0TVE9OTA2MTNGN0M=")
		.header("Content-Type","application/json")
		.body(payloads.jiraCreateBug(hs.get("projectKey"),hs.get("summary"),hs.get("issueType")))
		
		.when()
		.post("rest/api/3/issue")
		
		.then()
		.assertThat().statusCode(201)
		.extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	id = js.getString("id");
	System.out.println(id);
		
	}
	
	@Test
	public void addAttachments() {
		
		RestAssured.baseURI = "https://akthemaurya1999-1737183789949.atlassian.net";
		
		   String response =  given()
		//	.pathParam("key", "id")
			.header("Content-Type","multipart/form-data; boundary=<calculated when request is sent>")
			.header("Authorization","Basic YWt0aGVtYXVyeWExOTk5QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBuOFN5X3NLeVUzbUh6Sm5uZzBRTW1rbGxRLTlPOFNFVE55cW5Lbkk3VGJBZWtfWEJCbHlqU3Y5S2p2NnBPVUJQbVVyWWgyczRBNVZBTHg1Zy1oTDU3Z3NMVVJKS0ZWTjhsTEUzZGpjSEN1TWtQM1BFZEV4cEh3emZBLVExLVFDTXB5cTgyNFVkWHRzRk5NRUNQMGpSX2FWaGZUNnM1ZlNYeHQ0cm52c2p0TVE9OTA2MTNGN0M=")
			.header("X-Atlassian-Token","no-check")
			.multiPart("file",new File("C://Users//akthe//Pictures//Screenshots//Screenshot 2024-10-27 000120.png"))
			
			.when()
			.post("rest/api/3/issue/"+id+"/attachments")
		//	.post("rest/api/3/issue/{key}/attachments")
			
			.then().log().all()
			.assertThat().statusCode(200)
			.extract().response().toString()
			;
		   
		   JsonPath js = new JsonPath(response);
		   String URL = js.getString("self");
		   System.out.println(URL);
		
	}
	
	
	
	
	@DataProvider(name = "JiraInfo")
	public Object[][] getData() {
		
		HashMap<String,String> hs = new HashMap<String,String>();
		hs.put("projectKey","SCRUM");
		hs.put("summary","Creation of bug via REST Assured Code with Attachments");
		hs.put("issueType","Bug");
		
		return new Object[][] {{hs}};
		
	}

}
