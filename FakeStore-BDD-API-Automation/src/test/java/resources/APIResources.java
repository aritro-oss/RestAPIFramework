package resources;

public enum APIResources {

	getProductAPI("/products"),
	addProductAPI("/products"),
	deleteProductAPI("/products/{id}");


	private String resource;

	private APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
