Feature: Login Functionality

  @Login 
  Scenario: Validation of Login Functionality
    Given User launches the browser and navigates to the application url
    When User click on login button
    Then User enters the username and password
    Then user click on submit button
    And Home page is displayed
    And User quits the browser
    
    
    
    
    