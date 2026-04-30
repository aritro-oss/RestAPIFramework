package Test;

import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.Api;
import POJO.Courses;
import POJO.POJO;
import POJO.WebAutomation;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuth {
	@Test
	public void OAuthtest() {
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
		baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all().post(baseURI).asString();

		System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");

		/*
		 * String response2 = given() .queryParam("access_token", accessToken)
		 * .when().log().all()
		 * .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		 */
		POJO p = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(POJO.class);
		System.out.println(p.getLinkedIn());
		System.out.println(p.getInstructor());

		List<Api> apiCourses = p.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
				
			}
		}
		
		ArrayList<String> ar = new ArrayList();
		List<WebAutomation> webAutomation = p.getCourses().getWebAutomation();
		for(int i =0;i<webAutomation.size();i++) {
			ar.add(webAutomation.get(i).getCourseTitle());
		}
		List<String> expected = Arrays.asList(courseTitles);
		Assert.assertTrue(ar.equals(expected));
	}
}
