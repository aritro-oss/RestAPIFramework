package Test;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.ReusableMethods;
import Demo.payload;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class ApiAutomationDay1 {
	@Test
	public void day1() throws IOException {

		/*
		 * given - all input details 
		 * when - Submit the API- resource, http method 
		 * then - validate the response
		 */

		baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qa123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Aritro\\Documents\\API Notes\\sample.json")))).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().asString();

		System.out.println(response);

		// Add place -> update Place with new address-> Get place to validate if new
		// address is present in response

		JsonPath js = new JsonPath(response); // for passing json
		String placeId = js.getString("place_id");
		System.out.println("Place Id: " + placeId);
		
		// update place
		String newAddress = "60 summer walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// get place
		
		String getAddress = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().asString();
				
		JsonPath js1 = ReusableMethods.rawToJason(getAddress);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(newAddress, actualAddress);
	}
}
