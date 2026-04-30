package Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class GetAndPost {

	@Test
	public void testGet() {

		baseURI = "https://api.restful-api.dev";
		given().get("/objects").then().statusCode(200).body("name[1]", equalTo("Apple iPhone 12 Mini, 256GB, Blue"))
				.body("name[1]", containsString("Apple"));
	}

	@Test
	public void testPost() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "Test");
//		map.put("data.year[0]", "2020");

		JSONObject data = new JSONObject();
		data.put("year", 2020);

		JSONObject json = new JSONObject();
		json.put("name", "Test");
		json.put("data", data);
		System.out.println(json.toJSONString());

		baseURI = "https://api.restful-api.dev";

		given().header("Content-Type", "application/json").contentType(JSON).accept(JSON)
				.body(json.toJSONString()).when().post("/objects").then().statusCode(200);

	}
}
