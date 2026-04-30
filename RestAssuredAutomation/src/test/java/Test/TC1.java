package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TC1 {

	@Test
	public void test1() {

		Response response = get("https://api.restful-api.dev/objects");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void test2() {

		baseURI = "https://api.restful-api.dev";
		given().get("/objects").then().statusCode(200).body("data.color[0]", equalTo("Cloudy White")).log().all();
	}
}
