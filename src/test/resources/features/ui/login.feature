@ui
Feature: Login
  As a user
  I want to log into OpenMRS
  So that I can access the system

  Scenario: Successful login with valid credentials
    Given I am on the OpenMRS login page
    When I log in with valid credentials
    Then I should land on the home page
