@smokeTest
@login
Feature: Login Action

  Background: Generic Steps

    Given User Navigate to Home Page
    When User Navigate to LogIn Page

  @loginUnsuccessful
  Scenario: Unsuccessful Login with invalid Credentials
    Given I insert the user tes@gmail.com
    And I go to insert pwd
    When I insert the password test123
    And I do summit
    Then Message displayed Login unsuccessfully


  @loginSuccessful
  Scenario Outline: Successful Login with valid Credentials of a role
    Given I insert the user <UserName>
    And I go to insert pwd
    When I insert the password <Password>
    And I do summit
    Then Message displayed Login Successfully for the user <UserName>

    Examples:
      | userRole   | UserName            | Password |
      | final user | test2@gmail.com      | test321  |
      | admin user | admintest@gmail.com | test321  |






