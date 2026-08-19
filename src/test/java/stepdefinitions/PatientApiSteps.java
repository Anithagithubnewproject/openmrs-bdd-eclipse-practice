package stepdefinitions;

import api.PatientApiClient;
import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class PatientApiSteps {

    private final TestContext context;
    private final PatientApiClient apiClient = new PatientApiClient();
    private Response lastResponse;

    public PatientApiSteps(TestContext context) {
        this.context = context;
    }

    @Given("a patient exists with the following details")
    public void a_patient_exists_with_the_following_details(DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        context.setGivenName(row.get("givenName"));
        context.setFamilyName(row.get("familyName"));

        String requestBody = PatientPayloadBuilder.build(
                row.get("givenName"), row.get("familyName"), row.get("gender"), row.get("birthdate"));

        Response response = apiClient.createPatient(requestBody);
        assertEquals(response.statusCode(), 201, "Precondition failed: could not create patient via API");

        context.setPatientUuid(response.jsonPath().getString("uuid"));
    }

    @When("I create a patient via the API with valid details")
    public void i_create_a_patient_via_the_api_with_valid_details() {
        String requestBody = PatientPayloadBuilder.build("Arjun", "Mehta", "M", "1985-11-02");
        lastResponse = apiClient.createPatient(requestBody);
        context.setPatientUuid(lastResponse.jsonPath().getString("uuid"));
    }

    @When("I create a patient via the API with an incomplete payload")
    public void i_create_a_patient_via_the_api_with_an_incomplete_payload() {
        lastResponse = apiClient.createPatient(PatientPayloadBuilder.buildIncomplete());
    }

    @When("I retrieve that patient via the API")
    public void i_retrieve_that_patient_via_the_api() {
        lastResponse = apiClient.getPatient(context.getPatientUuid());
    }

    @Then("the API response status should be {int}")
    public void the_api_response_status_should_be(int expectedStatus) {
        assertEquals(lastResponse.statusCode(), expectedStatus);
    }

    @Then("the created patient should have a valid UUID")
    public void the_created_patient_should_have_a_valid_uuid() {
        assertNotNull(context.getPatientUuid());
        assertTrue(context.getPatientUuid().length() > 0);
    }

    @Then("the retrieved patient's name should match")
    public void the_retrieved_patients_name_should_match() {
        String actualGivenName = lastResponse.jsonPath().getString("person.names[0].givenName");
        assertEquals(actualGivenName, context.getGivenName());
    }
}
