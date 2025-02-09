package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Locations;

public class TestDataBuild {

	public static AddPlace addPlacePayload(String name, String language, String place) {
		
		AddPlace ap =new AddPlace();
		ap.setAccuracy(78);
		ap.setAddress(place);
		ap.setLanguage(language);
		ap.setName(name);
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
		
		return ap;
	}
	
	public static String deletePlacePayload(String placeid) {
		
		return ("{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}");
	}
}
