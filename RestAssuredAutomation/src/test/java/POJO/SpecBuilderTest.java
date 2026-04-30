package POJO;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

	@Test
	public void serializationTest() {
		baseURI = "https://rahulshettyacademy.com";
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

		RequestSpecification req = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();

		ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification request = given().spec(req).body(p);

		Response res = request.when().post("/maps/api/place/add/json")
		.then().spec(respSpec).extract().response();

		System.out.println(res.asString());
	}
}
