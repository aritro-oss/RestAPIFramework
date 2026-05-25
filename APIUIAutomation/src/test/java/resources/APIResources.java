package resources;

public enum APIResources {

	loginAPI("/web/index.php/auth/validate"),
	createEmployeeAPI("/web/index.php/api/v2/pim/employees");

	private String resource;

	private APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
