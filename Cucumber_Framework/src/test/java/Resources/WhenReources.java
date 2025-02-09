package Resources;

// enum is special class in java which has collection of constants and methods
public enum WhenReources {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	UpdatePlaceAPI("maps/api/place/update/json");

	private String resource;
	
	WhenReources(String string) {
		// TODO Auto-generated constructor stub
		this.resource = string;
	}
	
	public String getResource() {
		return resource;
	}
	
}

/* Enum points : 
 * Modify the 'class' with 'enum' keyword i.e. public enum WhenReources{}
 * Defining all the methods at a time separated by ',' if more methods are there else ';' to break
 * Define the contructor of enum class
 * Pass the resource i.e. 'string' value from stepDefinition which fetches from feature file (value passed from 
 *   feature file should be same as defined in enum class i.e. feature file 'AddPlaceAPI' and enum 'AddPlaceAPI("maps/api/place/add/json")'
 *   here 'AddPlaceAPI' naming convention should be same
 * return the resource 'string'
 *  
 */