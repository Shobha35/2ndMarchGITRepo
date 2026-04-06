@createCustomer @sanity
Feature: Creation of new Customer

  Background: 
    Given User opens the browser URL
    And user enters the username as "mngr658417"
    And user enters the password as "nutUqeh"
    When user clicks on login button

  Scenario: Create new customer with all details using DataTable
    Given user is on the homepage of the application
    And user clicks on New Customer link
    And user enters customer details
      | name  | gender | dob        | address   | city  | state     | pin    | mobile     | email             | password  |
      | Tarun | male   | 30-10-2010 | Ward No 5 | Ilkal | Karnataka | 587125 | 1234567890 | taru123@gmail.com | Taru@1234 |
    When user clicks on submit button
    Then a new customer will be created
    And user captures the customer id
    #And user closes the browser
    Given user clicks on New Account link
    And user fetches the customer id from excel
    And user enters initial deposit as "5000"
    When user clicks on asubmit button
    Then new account should be created successfully
    And user captures the account id
    And verify account id in excel
    When user clicks on logout link
    Then user should be redirected to login page
    And user should see login form again
    And user closes the browser
