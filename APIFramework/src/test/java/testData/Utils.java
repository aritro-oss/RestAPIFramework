package testData;

import static io.restassured.RestAssured.baseURI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
public RequestSpecification requestSpecification() {
	baseURI = "https://rahulshettyacademy.com";

	req = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON).build();
	
	return req;
}
}
