package Test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestsOnLocalAPI {
/*	@Test
	public void get() {
		baseURI = "http://localhost:3000";
		given().get("/users").then().statusCode(200).log().all();
	}

	@Test
	public void post() {
		JSONObject request = new JSONObject();
		request.put("username", "Sam");
		request.put("email", "Sam@test.com");
		request.put("role", "member");
		
		baseURI = "http://localhost:3000";
		
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).when().post("/users").then().statusCode(201).log().all();
	}

	@Test
	public void put() {
		JSONObject request = new JSONObject();
		request.put("username", "Sam");
		request.put("email", "Sam@test.com");
		request.put("role", "member");
		
		baseURI = "http://localhost:3000";
		
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).when().put("/users/QMCqSmZq2w4").then().statusCode(200).log().all();
	}
	
	
	@Test
	public void patch() {
		JSONObject request = new JSONObject();
		request.put("username", "AltmanNew");
		
		baseURI = "http://localhost:3000";
		
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).when().patch("/users/QMCqSmZq2w4").then().statusCode(200).log().all();
	}
	*/
	
	@Test
	public void delete() {
		baseURI = "http://localhost:3000";
		given().delete("/users/nNNk7VZO3AQ").then().statusCode(200).log().all();

	}
}
