package steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import pojos.Launch1;

public class stepDefinition {
	
	
	public static RequestSpecification req, request;
	public static ResponseSpecification res;
	public String resp;
	public static Response response;
    public stepDefinition() {
           RestAssured.baseURI = "https://api.spacexdata.com";
    }
    
    @Before
    public RequestSpecification requestSpecification() throws FileNotFoundException {
        
    	if(req==null)
		{
        PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
        System.out.println("hello");
        req = new RequestSpecBuilder().setBaseUri(RestAssured.baseURI)
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
        return req;
		}
		return req;
    	
    }
    
    
    @When("I execute GET request at URL: {string}")
    public void iexecuteGETRequestAt(String uri) throws IOException {
    	String url=RestAssured.baseURI+uri;
    	System.out.println(url);
    	res =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	request =given().spec(requestSpecification());
    	response= request.when().get(uri);
        
    }
    
    
    
    @Then("status code should be {int}")
    public void status_code_should_be(Integer int1) {
       	assertEquals(response.getStatusCode(),200);
    }
    
    @Then("the response header should be {string}")
    public void the_response_header_should_be(String string1) {
        String headerValue=response.getHeader("Content-Type");
        assertTrue(headerValue.contains(string1));
    }
    
    @Then("the Status Line should be {string}")
    public void the_status_line_should_be(String string) {
        assertEquals(response.getStatusLine(),string);
    }

    
    @Then("for successful launches the {string} should be {string}")
    public void for_successful_launches_the_should_be(String key, String expectedval) {
        resp=response.asString();
        System.out.println(resp);
        JsonPath js=new JsonPath(resp);
           
        String actualval=js.getString("cores[0].landing_success");
        
        
        if(js.getString("success").equals("true"))
        assertEquals(actualval,expectedval);
    }




}
