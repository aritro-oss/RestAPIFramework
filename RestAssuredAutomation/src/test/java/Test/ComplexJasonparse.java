package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJasonparse {
	@Test
	public void jsonParse() {

		JsonPath js = new JsonPath(payload.coursePrice());

		int count = js.getInt("courses.size()");
		System.out.println(count);

		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		String title = js.getString("courses[0].title");
		System.out.println(title);

		for (int i = 0; i < count; i++) {
			String title1 = js.getString("courses[" + i + "].title");
			String coursePrices = js.getString("courses[" + i + "].price");

			System.out.println(title1);
			System.out.println(coursePrices);
		}

		for (int i = 0; i < count; i++) {
			String targetTitle = "RPA";
			if (js.getString("courses[" + i + "].title").equals(targetTitle)) {
				String rpaCopies = js.getString("courses[" + i + "].copies");
				System.out.println(rpaCopies);
				break;
			}
		}
		int amount = 0;

		for (int i = 0; i < count; i++) {
			int courseAmount = js.getInt("courses[" + i + "].price");
			int totalCopies = js.getInt("courses[" + i + "].copies");
			amount += courseAmount * totalCopies;
		}

		if (amount < totalAmount) {
			System.out.println("Amount: " + amount + " is less than " + totalAmount);
		}
		else{
			System.out.println("Amount: " + amount + " = " + totalAmount);
		}

	}
}
