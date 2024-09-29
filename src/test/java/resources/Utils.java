package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification Req;      //It help to add multiple request
	public RequestSpecification requestSpecification() throws IOException
	{
		if(Req==null)   //if req=null it will get the req by executing below syntax, if it is not null it goto second test case.
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		Req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return Req;    
		}
		return Req;   //This line helps to fetch 2nd test case from cucumber instead of replace same data to first Testcase.
	}
	
	//Optimize BaseURL 
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();       //Property Class helps to scan any file name which having properties extension
		FileInputStream fis = new FileInputStream("C:\\Users\\yadav.m\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);  //prop have ability to store proper extension data but it dont know where actually property locate, but fis object know that, so by using load method we combine/bind both.
		return prop.getProperty(key);
		
	}
	
	public static String getJsonPath(Response response, String key)
	{
	    String Resp=response.asString();
	    JsonPath js = new JsonPath(Resp);
	    return js.get(key).toString();
	}
	
	
	
}
