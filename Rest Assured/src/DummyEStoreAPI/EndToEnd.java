package DummyEStoreAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import files.payloads;

public class EndToEnd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";	
		String username ="aman107@yopmail.com";
		String password ="Qwerty@123";
		
		
		// Login
		Login_POJO login = new Login_POJO();
		login.setUserEmail(username);
		login.setUserPassword(password);
		
		String response1 = given().relaxedHTTPSValidation() // to bypass all the certifications needed
		.header("Content-Type","application/json")
		.body(login)
		
		.when()
		.post("api/ecom/auth/login")
		.then()
	    .assertThat().statusCode(200).extract().response().asString() ;
		
		JsonPath js = new JsonPath(response1);
		String token = js.get("token");
		String userId = js.get("userId");		
		
		
		// Create Product
		String response2 =given()
		.header("Authorization",token)
		.formParam("productName", "Aman") .formParam("productAddedBy", userId) .formParam("productCategory", "Cars")
		.formParam("productSubCategory", "Porshe") .formParam("productPrice", "300000") .formParam("productDescription", "911 GTR3")
		.formParam("productFor", "Legends")
		.multiPart("productImage",new File("C://Users//akthe//Downloads//porsche-zoom2 (5)-min (1).png"))
		
		.when()
		.post("api/ecom/product/add-product")
		
		.then()
		.assertThat().statusCode(201).body("message", equalTo("Product Added Successfully"))
		.extract().response().asString();
		
		JsonPath js2 = new JsonPath(response2);
		String productId = js2.get("productId");
		
		
		// Create Order
		Sub_orders_pojo_request suborder1 = new Sub_orders_pojo_request();
		suborder1.setCountry("India");
		suborder1.setProductOrderedId(productId);
		
		List<Sub_orders_pojo_request> countrylist = new ArrayList<Sub_orders_pojo_request>();
		countrylist.add(suborder1);     // if multiple then use suborder2, suborder3 and add them i.e. push back them
		
		CreateOrder_POJO_Request orders = new CreateOrder_POJO_Request();
		orders.setOrders(countrylist);
		
		CreateOrder_POJO_Response response3 = given()
		.header("Authorization",token)
		.header("Content-Type","application/json")
		.body(orders)
		
		.when()
		.post("api/ecom/order/create-order")
		
		.then().log().all()
		.assertThat().statusCode(201).body("message", equalTo("Order Placed Successfully"))
		.extract().response().as(CreateOrder_POJO_Response.class);;
		
		List<String> ordersIDs = response3.getOrders();
		String orderid = ordersIDs.getFirst();
		
		
		// View Order Details
		given()
		.header("Authorization",token)
		.queryParam("id", orderid)
		
		.when()
		.get("api/ecom/order/get-orders-details")
		
		.then()
		.assertThat().statusCode(200).body("message", equalTo("Orders fetched for customer Successfully"));
		
		// Delete Product
		given()
		.pathParam("key", productId)
		.header("Authorization",token)
		
		.when()
		.delete("api/ecom/product/delete-product/{key}")
		
		.then()
	.assertThat().statusCode(200).body("message", equalTo("Product Deleted Successfully"));
	
		System.out.println("Every Api executed successfully");
	}

}
