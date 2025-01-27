package GraphQL;

import static io.restassured.RestAssured.*;

public class Mutation_Query_Intro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Query
		String response = given()
		.header("Content-Type","application/json")
		.body("{\"query\":\"query ($characterId: Int!, $episodeId: Int!) {\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: 17289) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    id\\n    name\\n    air_date\\n    episode\\n    created\\n  }\\n  characters(filters: {name: \\\"Shadow\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {episode: \\\"1079\\\"}) {\\n    result {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":11791,\"episodeId\":12435}}")
		
		.when()
		.get("https://rahulshettyacademy.com/gq/graphql")
		
		.then()
		.assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		// Mutation 
		String responsequery = given()
				.header("Content-Type","application/json")
				.body("{\"query\":\"mutation ($locationName: String!, $characternName: String!, $episodeName: String!) {\\n  createLocation(location: {name: $locationName, type: \\\"City\\\", dimension: \\\"112\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds: [17279]) {\\n    locationsDeleted\\n  }\\n  createCharacter(character: {name: $characternName, type: \\\"Solo Warrior\\\", status: \\\"motivated\\\", species: \\\"hyped\\\", gender: \\\"male\\\", image: \\\"jpg\\\", originId: 17287, locationId: 17287}) {\\n    id\\n  }\\n  deleteCharacters(characterIds: 173) {\\n    charactersDeleted\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date: \\\"19-09-1999\\\", episode: \\\"1079\\\"}) {\\n    id\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"Uttar Pradesh\",\"characternName\":\"Dark House\",\"episodeName\":\"Manifest\"}}")
				
				.when()
				.post("https://rahulshettyacademy.com/gq/graphql")
				
				.then()
				.assertThat().statusCode(200).extract().response().asString();
				
				System.out.println(responsequery);
		
	}

}
