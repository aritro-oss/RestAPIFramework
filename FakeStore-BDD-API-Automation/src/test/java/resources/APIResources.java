package resources;

public enum APIResources {

	getProductAPI("/products"),
	addProductAPI("/products"),
	deleteProductAPI("/products/{id}"),
	updateProductAPI("/products/{id}"),
	addUserAPI("/users"),
	authLoginAPI("/auth/login");


	private String resource;

	private APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
