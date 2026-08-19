@ui
Feature: Patient Search
  As a clinic user
  I want to search for a patient
  So that I can find their record quickly

  Background:
    Given I am logged into OpenMRS
    And I am on the patient search page

  Scenario: Find a recently created patient by name
    Given a patient exists with the following details
      | givenName | familyName | gender | birthdate  |
      | Priya     | Sharma     | F      | 1990-05-14 |
    When I search for the patient by name
    Then the patient should appear in the search results

  Scenario: Search for a patient that does not exist
    When I search for a patient named "Nonexistent Person"
    Then no matching patients should be found
