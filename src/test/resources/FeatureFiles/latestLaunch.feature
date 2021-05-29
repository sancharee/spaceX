@latest
Feature: Test for spaceX launch report

 Scenario: Validate status code for GET request
    When I execute GET request at URL: "/v4/launches/latest"
    Then status code should be 200
    And the Status Line should be "HTTP/1.1 200 OK"
    And the response header should be "application/json"
    And for successful launches the "landing_success" should be "true" 
 
 
    