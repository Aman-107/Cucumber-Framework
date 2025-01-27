package files;

public class payloads {

	public static String addPlace(String name,String address) {
		
		String body = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \""+name+"\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \""+address+"\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"Apna Ghar\",\r\n"
				+ "    \"Nizzi Ghar\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"Hindi-IN\"\r\n"
				+ "}";
		
		return body;	
	}
	
	public static String updatePlace(String place_id, String updateAddress) {
		
		String body = "{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+updateAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
		
		return body;
	}
	
	public static String deletePlace(String place_id) {
		
		String body = "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
		
		return body;
	}
	
	public static String complexNestedJson() {
		
		String body = "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
		
		return body;
	}

	public static String addBook(String isbn,String aisle) {
		String body = "{\r\n"
				+ "\"name\":\"Shadow Book house of Darkness\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Aman Kumar Maurya\"\r\n"
				+ "}";
		
		return body;
	}
	
	public static String jiraCreateBug(String projectKey, String summary, String issueType) {
		
		String body = "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \""+projectKey+"\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \""+summary+"\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \""+issueType+"\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
		
		return body;
	}
	
//	public static String estoreLogin(String username, String password) {
//		return ("{\r\n"
//				+ "    \"userEmail\": \""+username+"\",\r\n"
//				+ "    \"userPassword\": \""+password+"\"\r\n"
//				+ "}");
//	}
//	
//	public static String estoreCreateOrder(String productId) {
//		return ("{\r\n"
//				+ "    \"orders\": [\r\n"
//				+ "        {\r\n"
//				+ "            \"country\": \"India\",\r\n"
//				+ "            \"productOrderedId\": \""+productId+"\"\r\n"
//				+ "        }\r\n"
//				+ "    ]\r\n"
//				+ "}");
//	}
}
