package stepDefinitions;

import java.io.ObjectInputFilter.Config;

import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import resources.ConfigReader;

public class Hooks extends Base{
	@Before
	public void setup() {
		RestAssured.baseURI = ConfigReader.getProperty("baseURI");
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
