package Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PutPatchDelete {
	String id;
	@Test
	public void testPut() {
		baseURI = "https://api.restful-api.dev";

		// build JSON
		JSONObject data = new JSONObject();
		data.put("year", 2020);

		JSONObject json = new JSONObject();
		json.put("name", "Test");
		json.put("data", data);

		// Post
		Response res = given().contentType(JSON).body(json.toJSONString()).when().post("/objects");

		id = res.jsonPath().getString("id");
		System.out.println("Created id: " + id);
		
		res.then().log().all();
		
		//PUT
		given().header("Content-Type", "application/json").contentType(JSON).accept(JSON).body(json.toJSONString())
				.when().put("/objects/" + id).then().statusCode(200).log().all();

	}
	/*
	@Test
	public void testPatch() {
		baseURI = "https://api.restful-api.dev";

		// build JSON
		JSONObject data = new JSONObject();
		data.put("year", 2021);

		JSONObject json = new JSONObject();
		json.put("name", "Test");
		json.put("data", data);

		// Post
		Response res = given().contentType(JSON).body(json.toJSONString()).when().post("/objects");

		String id = res.jsonPath().getString("id");
		System.out.println("Created id: " + id);

		JSONObject patchData = new JSONObject();
		patchData.put("year", 2026);
		
		JSONObject patchJson = new JSONObject();
		patchJson.put("data", patchData);

		//PUT
		given().header("Content-Type", "application/json").contentType(JSON).accept(JSON).body(patchJson.toJSONString())
				.when().patch("/objects/" + id).then().statusCode(200).log().all();

	}
	*/
	@Test
	public void testDelete() {
		baseURI = "https://api.restful-api.dev";

		//PUT
		given().delete("/objects/" + id).then().statusCode(200).log().all();

	}
}
