package Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src//test//java//features//placeValidations.feature" , 
plugin="json:target/jsonReports/cucumber-report.json" ,glue= {"stepDefination"})
public class TestRunner {

}

// ,tags= "@DeletePlaceAPI"

/* To run in terminal -> file source -> cmd -> command
 * 
 * mvn test 
 *  will run all the test scenario present in the framework
 *  
 * mvn test -Dcucmber.options="--tags @AddPlaceAPI"
 *  will run specific 'tags' scenario only
 *  
 *  Generating reports -> 
 *  plugin = "json:target/jsonReports/cucmber-report.json" 
 *  {location is made as per the documentation of github} (https://github.com/damianszczepanik/maven-cucumber-reporting)
 *  and import pom.xml from github above 'dependencies' and update 'version' remove 'classification'
 *  then run with -> mvn test verify -> generate parallely report
 */



