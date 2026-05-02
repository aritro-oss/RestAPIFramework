package StepDefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils {
	RequestSpecification res;
	ResponseSpecification respSpec;
	Response resp;
	TestDataBuild td = new TestDataBuild();
	static String placeID;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(td.addPlacePayload(name, language, address));

	}

	@When("user calls {string} with {string}  http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);

		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("post"))
			resp = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("get"))
			resp = res.when().get(resourceAPI.getResource());
	}

	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {
		assertEquals(resp.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		assertEquals(getJsonPath(resp, key), value);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		placeID = getJsonPath(resp, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_with_http_request(resource, "GET"); 
		String actualName = getJsonPath(resp, "name");
		assertEquals(actualName, expectedname);
	}
	
	@Given("Deleteplace Payload")
	public void deleteplace_payload() throws IOException {
	   res = given().spec(requestSpecification()).body(td.deletePlacePayload(placeID));
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(resp.getStatusCode(), 200);
	}
	@Then("key in response body is {string}")
	public void key_in_response_body_is(String key, String value) {

		assertEquals(getJsonPath(resp, key), value);
	}
}
