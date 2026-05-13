package StepDefinitions;

import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddUser;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import static org.testng.Assert.*;

public class stepDefinitions {
	Response response;
	TestDataBuilder td = new TestDataBuilder();
	int id;
	String username;
	String password;

	@Given("the API base url is set")
	public void the_api_base_url_is_set() {
	}

	@When("I call {string} with {string} request with {string}")
	public void i_call_with_request_with(String resource, String method, String payloadType) {
		APIResources resourceAPI = APIResources.valueOf(resource);

		if (method.equalsIgnoreCase("GET")) {
			response = given().spec(Utils.getRequestSpec()).when().get(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("POST")) {
			Object payload = td.getPayload(payloadType);

			response = given().spec(Utils.getRequestSpec()).body(td.getPayload(payloadType)).when()
					.post(resourceAPI.getResource());
			if (payloadType.equalsIgnoreCase("addUserPayload")) {
				AddUser user = (AddUser) payload;

				username = user.getUsername();
				password = user.getPassword();

				System.out.println("Created User: " + username);

			}
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = given().spec(Utils.getRequestSpec()).pathParam("id", id).when()
					.delete(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("PUT")) {
			response = given().spec(Utils.getRequestSpec()).pathParam("id", id)
					.body(new TestDataBuilder().updateProductPayload()).when().put(resourceAPI.getResource());
		}
	}

	@When("I send GET request to {string}")
	public void i_send_get_request_to(String endpoint) {
		response = RestAssured.given().log().all().when().get(endpoint);
	}

	@Then("the response {string} should be {int}")
	public void the_response_should_be(String status, Integer statusCode) {
		response.then().spec(Utils.getResponseSpec(statusCode));
	}

	@Then("the response should contain products")
	public void the_response_should_contain_products() {
		int count = response.jsonPath().getList("$").size();
		System.out.println("Total products: " + count);

		assertTrue(count > 0);
	}

	@Then("the response should add new product")
	public void the_response_should_add_new_product() {
		String title = response.jsonPath().getString("title");
		assertEquals(title, "New Product");
	}

	@Then("the response should be deleted")
	public void the_response_should_be_deleted() {
	}

	@Then("the response should be updated")
	public void the_response_should_be_updated() {
		String title = response.jsonPath().getString("title");
		assertEquals(title, "updated Product");
	}

	@Then("the response should add new user")
	public void the_response_should_add_new_user() {
		id = response.jsonPath().getInt("id");
		System.out.println("New ID: " + id);

	}

	@When("I login with created user")
	public void i_login_with_created_user() {
		response = given().spec(Utils.getRequestSpec()).body(td.authLoginPayload(username, password)).when().post("/user/login");
	}

}
