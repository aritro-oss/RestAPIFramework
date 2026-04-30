package Test;

import org.testng.annotations.Test;

import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetails;
import POJO.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITest {
	@Test
	public void ecommerceAPITest() {
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequest loginReq = new LoginRequest();
		loginReq.setUserEmail("test_test@gmail.com");
		loginReq.setUserPassword("Test@123");

		RequestSpecification reqLogin = given().log().all().spec(req).body(loginReq);
		LoginResponse loginResp = reqLogin.when().post("/api/ecom/auth/login").then().extract().response()
				.as(LoginResponse.class);

		String token = loginResp.getToken();
		String userID = loginResp.getUserId();

		// Add Product

		RequestSpecification addProductbaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification reqAddProd = given().log().all().spec(addProductbaseReq).multiPart("productName", "Laptop")
				.multiPart("productAddedBy", userID).multiPart("productCategory", "Electronics")
				.multiPart("productSubCategory", "laptops").multiPart("productPrice", "35000")
				.multiPart("productDescription", "Acer").multiPart("productFor", "all")
				.multiPart("productImage", new File("C:\\Users\\Aritro\\Downloads\\191327.jpg"));

		String addProductResponse = reqAddProd.when().post("/api/ecom/product/add-product").then().log().all().extract()
				.response().asString();

		JsonPath js = new JsonPath(addProductResponse);
		String prodId = js.getString("productId");
		System.out.println(prodId);

		// Create Order

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderId(prodId);

		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		orderDetailList.add(orderDetails);

		Orders orders = new Orders();
		orders.setOrders(orderDetailList);

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
		String addOrderResp = createOrderReq.when().post("api/ecom/order/create-order").then().log().all().extract()
				.response().asString();

		System.out.println(addOrderResp);

		// Delete Product

		RequestSpecification deleteOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		RequestSpecification deleteProdReq = given().log().all().spec(deleteOrderBaseReq).pathParam("productId",
				prodId);

		String deleteResp = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().asString();
		System.out.println(deleteResp);
	}
}
