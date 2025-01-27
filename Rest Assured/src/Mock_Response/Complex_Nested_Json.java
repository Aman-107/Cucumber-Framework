package Mock_Response;

import org.testng.Assert;

import files.payloads;
import io.restassured.path.json.JsonPath;

public class Complex_Nested_Json {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payloads.complexNestedJson());
		
		// 1. Print No of courses returned by API
		int courseSize = js.getInt("courses.size()");
		
		// 2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		// 3. Print Title of the first course
		String firstCourse = js.getString("courses[0].title");
		
		System.out.println(courseSize + " " + purchaseAmount + " " + firstCourse);
		
		// 4. Print All course titles and their respective Prices
		for(int i=0; i<courseSize; i++) {
			String courseTitles = js.getString("courses["+i+"].title"); // in loop we can't directly use "i" instead concat with '+i+'
			int price = js.getInt("courses["+i+"].price");
			System.out.println(courseTitles + " " + price);
		}
		int sum = 0;
		// 5. Print no of copies sold by RPA Course
		for(int i=0; i<courseSize; i++) {
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println(js.getInt("courses["+i+"].copies"));
			} // for optimising use break;
			sum += js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
		}
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		Assert.assertEquals(sum, purchaseAmount);
	}

}

/*
1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount
*/