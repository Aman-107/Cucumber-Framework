package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification reqspec;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		PrintStream log = new PrintStream(new FileOutputStream("loggigng.txt"));
		
		reqspec = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		
		return reqspec;
			
	}
	
	public static String getGlobalProperty(String baseUrl) throws IOException {
		
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("E://SDET Codes Ecllipse//Cucumber_Framework//src//test//java//Resources//GlobalProperties.properties");
		properties.load(file);
		
		return properties.getProperty(baseUrl);
	}
	
	public String getJsonPath(Response response,String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		String value = js.get(key);
		return value;
	}
	
}


/* .addFilter(RequestLoggingFilter.logRequestTo(log)) -> method to log the console
 * 
 * PrintStream log = new PrintStream(new FileOutputStream("loggigng.txt"));
 * 
 * PrintStream -> class used for returning printstream format since 'method' requires return type of printStream
 * new FileOutputStream -> class in Java to create file ("name.extension")
 *
 */
