package resources;

import pojo.AddProduct;

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
}
