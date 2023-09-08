Feature: Login Functionality
@Selenium
  Scenario: User is getting registered in the application
    Given User is on Home Page
    When User enters all the details
    Then User should get registered
@restAssured
  Scenario: User is doing a rest assured demo
    Given User sends a get request
    Then user should get a response