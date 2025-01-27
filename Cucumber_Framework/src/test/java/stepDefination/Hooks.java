package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {

	@Before("@DeletePlaceAPI")
	public void deletePlace() throws IOException {
	
		stepDefinations sd = new stepDefinations();
		
		if(stepDefinations.place_id == null) {
			
			sd.add_place_payload_with("Aman", "Bhojpuri", "Bihar");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Aman", "GetPlaceAPI");
		}
		
	} 
}



