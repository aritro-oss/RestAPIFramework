package StepDefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import resources.ConfigReader;

public class Hooks {
	
	@Before
	public void setUp() {
		RestAssured.baseURI = ConfigReader.getProperty("baseURI");

	}
}
