package POJO;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization {

	@Test
	public void serializationTest() {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setName("Frontline house");
		p.setLanguage("French-IN");
		p.setWebsite("http://google.com");
		p.setPhone_number("(+91) 983 893 3937");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		p.setLocation(loc);

		baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println(response);
	}
}
