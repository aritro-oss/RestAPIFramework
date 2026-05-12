package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseURI")).setContentType(ContentType.JSON).log(LogDetail.ALL)
				.build();

	}
	
	public static ResponseSpecification getResponseSpec(int statusCode) {
		return new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(ContentType.JSON).log(LogDetail.ALL).build();
		
	}
}
