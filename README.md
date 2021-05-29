# spaceXAPIAutomation : Rest-Assured_Cucumber_Junit_Maven

This project illustrates the validation of HTTP response GET request on the latest launch

#Technologies Used:<br>

RestAssured
cucumber
Junit
Maven




#Scenarios Automated:<br>
1. Execute a GET request https://api.spacexdata.com/v4/launches/latest.
2. Verify the status code in response
3. Verify the status line
4. Verify the response header
5. Verify in response body that for successful launches the "landing_success" should be "true"


#Running Test<br>
To run  test
    $ mvn test
