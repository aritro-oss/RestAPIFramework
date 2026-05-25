package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddEmployee;
import pojo.LoginData;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import base.Base;

public class StepDefinitions extends Base {
	Response response;
	TestDataBuilder td = new TestDataBuilder();
	int empNumber;
	APIResources resourceAPI;
	SessionFilter session = new SessionFilter();
	String cookie;

	@Given("I successfully login to OrangeHRM with {string} using {string} method")
	public void i_successfully_login_to_orange_hrm_with_using_method(String payload, String method) {
		resourceAPI = APIResources.loginAPI;
		LoginData ld = (LoginData) td.getPayload("loginDataPayload");

		response = given().spec(Utils.getFORMRequestSpec()).filter(session).redirects().follow(true)
				.formParam("username", ld.getUsername()).formParam("password", ld.getPassword())
				.post(resourceAPI.getResource());
		cookie = response.getCookie("orangehrm");
		System.out.println(cookie);
	}

	@When("I create new user data with {string} in {string}")
	public void i_create_new_user_data_with_in(String payload, String method) {
		resourceAPI = APIResources.createEmployeeAPI;

		AddEmployee ad = (AddEmployee) td.getPayload("addEmployeePayload");
		response =
			    given()
			        .spec(Utils.getJSONRequestSpec())
			        .cookie("orangehrm", cookie)
			        .header("Accept", "application/json")
			        .header("X-Requested-With", "XMLHttpRequest")
			        .header("Referer",
			            "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList")
			        .body(ad)
			    .when()
			        .post(resourceAPI.getResource());

			response.prettyPrint();

		response.prettyPrint();
		response.prettyPrint();
//		empNumber = response.jsonPath().getInt("data.empNumber");
	}

	@Then("the user should be created successfully")
	public void the_user_should_be_created_successfully() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("I store all created user data")
	public void i_store_all_created_user_data() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("I open the OrangeHRM application UI")
	public void i_open_the_orange_hrm_application_ui() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("I login using the created user credentials")
	public void i_login_using_the_created_user_credentials() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("I should be able to login using the new data")
	public void i_should_be_able_to_login_using_the_new_data() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
