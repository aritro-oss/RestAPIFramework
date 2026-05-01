package testData;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace addPlacePayload() {
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress("29, side layout, cohen 09");
	p.setName("Frontline house");
	p.setLanguage("French-IN");
	p.setWebsite("http://google.com");
	p.setPhone_number("(+91) 983 893 3937");
	List<String> myList = new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	p.setTypes(myList);
	Location loc = new Location();
	loc.setLat(-38.383494);
	loc.setLng(33.427362);
	p.setLocation(loc);
	return p;
	}
}
