@logout @sanity
Feature: Logout from application
 

  Background: User is logged into the application
    Given User opens the browser URL
    And user enters the username as "mngr654993"
    And user enters the password as "eqUsEdu"
    When user clicks on login button

  
  Scenario: User successfully logs out from homepage
    #When user clicks on logout link
    #Then user should be redirected to login page
    #And user should see login form again