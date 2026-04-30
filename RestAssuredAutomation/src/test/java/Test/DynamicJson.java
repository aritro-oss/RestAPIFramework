package Test;

import org.openqa.selenium.json.Json;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Demo.payload;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test(dataProvider = "data")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = 
		given().header("Content-type", "application/json").
		body(payload.addBook(isbn, aisle)).
		when().
		post("Library/Addbook.php")
		.then().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		
		System.out.println("ID: " + id);
		
		//given().delete("Library/Addbook.php" + id).then().statusCode(200);
	}
	
	@DataProvider(name = "data")
	public Object[][] getData() {
		
		//array = collection of multiple elements
		return new Object[][]  {
				{"isbn1", "aisle1"}, 
				{"isbn2", "aisle2"}, 
				{"isbn3", "aisle3"}
				};
	}
}
