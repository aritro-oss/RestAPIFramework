package resources;

import pojo.AddEmployee;
import pojo.LoginData;
import pojo.UserData;

public class TestDataBuilder {
	public LoginData loginDataPayload() {
		LoginData login = new LoginData();
		login.setUsername("Admin");
		login.setPassword("admin123");
		return login;
	}
	
	public UserData userDataPayload() {
		UserData ud = new UserData();
		ud.setUsername("TTest");
		ud.setPassword("Test123");
		ud.setStatus(true);
		ud.setUserRoleId(1);
		
		return ud;
	}
	
	public AddEmployee addEmployeePayload() {
		AddEmployee ad = new AddEmployee();
		ad.setEmployeeId("919");
		ad.setEmpPicture(null);
		ad.setFirstName("Test");
		ad.setLastName("25052026");
		ad.setMiddleName("");
		return ad;
	}

	public Object getPayload(String payloadType) {
		switch (payloadType) {
		case "loginDataPayload":
			return loginDataPayload();
		case "addEmployeePayload":
			return addEmployeePayload();
		default:
			throw new RuntimeException("Invalid payload: " + payloadType);
		}
	}
}
