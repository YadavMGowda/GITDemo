package resources;

import java.util.ArrayList;
import java.util.List;

import POJOSerialization.AddPlace;
import POJOSerialization.Location;

public class TestDataBuild {
	
 public AddPlace addPlacePayLoad(String name,String language,String address,String phone_number)
 {
	 AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number(phone_number);
		p.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
 }

 public String deletePlacePayload(String placeId)
	{
		return "{\r\n\"place_id\": \""+placeId+"\"\r\n}";
	}

}
