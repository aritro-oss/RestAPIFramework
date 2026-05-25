Feature: API UI automation

  Scenario: Create user data via API and validate in UI
    Given I successfully login to OrangeHRM with "loginDataPayload" using "POST" method
    When I create new user data with "addEmployeePayload" in "createEmployeeAPI"
    Then the user should be created successfully
    And I store all created user data
    When I open the OrangeHRM application UI
    And I login using the created user credentials
    Then I should be able to login using the new data
