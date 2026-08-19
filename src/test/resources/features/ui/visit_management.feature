@ui
Feature: Visit Management
  As a clinic user
  I want to start and end a visit for a patient
  So that clinical activity can be recorded against that visit

  Background:
    Given a patient exists with the following details
      | givenName | familyName | gender | birthdate  |
      | Priya     | Sharma     | F      | 1990-05-14 |
    And I am logged into OpenMRS
    And I have navigated to that patient's dashboard

  Scenario: Start a new visit for a patient
    When I start a new visit for the patient
    Then the patient should have an active visit

  Scenario: End an active visit for a patient
    Given the patient has an active visit
    When I end the active visit
    Then the patient should no longer have an active visit
