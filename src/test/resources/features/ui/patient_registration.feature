@ui
Feature: Patient Registration
  As a registration clerk
  I want to register a new patient through the UI
  So that they can be seen by a provider

  Background:
    Given I am logged into OpenMRS
    And I am on the patient registration page

  Scenario: Register a new patient with valid details
    When I register a new patient with the following details
      | givenName | familyName | gender | birthdate  |
      | Arjun     | Mehta      | M      | 1985-11-02 |
    Then the patient registration should be confirmed
