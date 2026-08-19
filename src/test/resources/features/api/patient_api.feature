@api
Feature: Patient API
  As a system integrator
  I want to manage patients through the REST API
  So that patient data can be created, retrieved, and validated programmatically

  Scenario: Create a new patient
    When I create a patient via the API with valid details
    Then the API response status should be 201
    And the created patient should have a valid UUID

  Scenario: Retrieve an existing patient
    Given a patient exists with the following details
      | givenName | familyName | gender | birthdate  |
      | Priya     | Sharma     | F      | 1990-05-14 |
    When I retrieve that patient via the API
    Then the API response status should be 200
    And the retrieved patient's name should match

  Scenario: Reject patient creation with an incomplete payload
    When I create a patient via the API with an incomplete payload
    Then the API response status should be 400
