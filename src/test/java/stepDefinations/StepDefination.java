package stepDefinations;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
public class StepDefination extends Utils    //Extends is inheritance Concept now StepDefination is treated as child class & Utils is Parent.
											//This help to inheriting all the methods of parent class to Child class
{
	RequestSpecification Res;
	ResponseSpecification ResSpec;
	Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;
  
    @Given("Add Place Payload {string} {string} {string} {string}")
    public void add_place_payload(String name, String language, String address, String phone_number) throws IOException 
    {
    // Write code here that turns the phrase above into concrete actions
	Res=given().spec(requestSpecification())
			.body(data.addPlacePayLoad(name, language, address, phone_number));
	}
    @When("User Calls {string} with {string} Http Request")
    public void user_calls_with_http_request(String resource, String method) {

    // Write code here that turns the phrase above into concrete actions
	//Constructor(Enum) will be called with value of resource which you pass
	APIResources resourceAPI=APIResources.valueOf(resource); 
	System.out.println(resourceAPI.getResource());
	
	ResSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	if(method.equalsIgnoreCase("POST"))
	 response =Res.when().post(resourceAPI.getResource()); //Value of resource help to take Http method we choose in cucumber & fetch same resource from Enum.
	else if(method.equalsIgnoreCase("GET"))
		response =Res.when().get(resourceAPI.getResource());
}
@Then("The API Call got Success with Status Code {int}")
public void the_api_call_got_success_with_status_code(Integer int1) 
{
    // Write code here that turns the phrase above into concrete actions
	assertEquals(response.getStatusCode(),200);

}
@Then("{string} in response body is {string}")
public void in_response_body_is(String keyValue, String Expectedvalue) 
	{
    // Write code here that turns the phrase above into concrete actions

    
    assertEquals(getJsonPath(response,keyValue),Expectedvalue);
    }

@Then("verify palce_Id created maps to {string} using {string}")
public void verify_palce_id_created_maps_to_using(String expectedName, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions
	//request Specification
	 place_id=getJsonPath(response,"place_id");
	Res=given().spec(requestSpecification()).queryParam("place_id",place_id );
	user_calls_with_http_request(resource, "GET");
	String actualName=getJsonPath(response,"name");
	assertEquals(actualName,expectedName);
}

@Given("Delete Place Payload")
public void delete_place_payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
   Res= given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
}

}




