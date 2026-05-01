package StepDefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import testData.TestDataBuild;
import testData.Utils;

public class stepDefinitions extends Utils{
	RequestSpecification res;
	ResponseSpecification respSpec;
	Response resp;
	TestDataBuild td = new TestDataBuild(); 
	
	
	@Given("Add Place Payload")
	public void add_place_payload() {

		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(requestSpecification()).body(td.addPlacePayload());

	}
	@When("user calls {string} with POST http request")
	public void user_calls_with_post_http_request(String string) {
		resp = res.when().post("/maps/api/place/add/json")
				.then().spec(respSpec).extract().response();
	}
	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {
	   assertEquals(resp.getStatusCode(), 200);
	   
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	   String response = resp.asString();
	   JsonPath js = new JsonPath(response);
	   assertEquals(js.get(key).toString(), value);	   
	}
}
