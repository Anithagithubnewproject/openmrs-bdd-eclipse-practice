package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PatientRegistrationPage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.Map;

import static org.testng.Assert.assertTrue;

public class PatientRegistrationSteps {

    private final PatientRegistrationPage registrationPage = new PatientRegistrationPage(DriverFactory.getDriver());

    @Given("I am on the patient registration page")
    public void i_am_on_the_patient_registration_page() {
        DriverFactory.getDriver().get(ConfigReader.baseUiUrl()
                + "/registrationapp/registerPatient.page?appId=referenceapplication.registrationapp.registerPatient");
    }

    @When("I register a new patient with the following details")
    public void i_register_a_new_patient_with_the_following_details(DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        registrationPage
                .enterGivenName(row.get("givenName"))
                .enterFamilyName(row.get("familyName"))
                .selectGender(row.get("gender").equalsIgnoreCase("M") ? "male" : "female")
                .enterBirthdate(row.get("birthdate"));
        registrationPage.submitRegistration();
    }

    @Then("the patient registration should be confirmed")
    public void the_patient_registration_should_be_confirmed() {
        assertTrue(registrationPage.isRegistrationConfirmed(), "Expected registration confirmation to be displayed");
    }
}
