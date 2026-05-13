package resources;

import pojo.AddProduct;
import pojo.AddUser;
import pojo.AuthLogin;
import pojo.UpdateProduct;

public class TestDataBuilder {
	public AddProduct addProductPayload() {
		AddProduct p = new AddProduct();
		p.setId(21);
		p.setTitle("New Product");
		p.setPrice(2.2);
		p.setDescription("new product");
		p.setCategory("new");
		p.setImage("");
		return p;

	}
	
	public UpdateProduct updateProductPayload() {
		UpdateProduct p = new UpdateProduct();
		p.setId(22);
		p.setTitle("updated Product");
		p.setPrice(2.2);
		p.setDescription("updated product");
		p.setCategory("updated");
		p.setImage("");
		return p;

	}
	
	public AddUser addUserPayload() {
		AddUser a = new AddUser();
		a.setId(1001);
		a.setUsername("mor_2314");
		a.setEmail("test@hotmail.com");
		a.setPassword("83r5^_");
		return a;
	}
	
	public String authLoginPayload(String username, String password) {
		return "{\n" +
	"\"username\": \"" + username + "\",\n" +
	"\"password\": \"" + password +"\",\n" +
	"}";
	}
	
	public Object getPayload(String payloadType) {
		switch(payloadType) {
		case "addProductPayload":
			return addProductPayload();
		case "updateProductPayload"	:
			return updateProductPayload();
		case "addUserPayload":
			return addUserPayload();
		default:
            throw new RuntimeException("Invalid payload: " + payloadType);
		}		
	}
}
