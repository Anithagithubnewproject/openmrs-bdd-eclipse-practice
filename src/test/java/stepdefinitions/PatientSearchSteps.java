package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PatientSearchPage;
import utils.ConfigReader;
import utils.DriverFactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PatientSearchSteps {

    private final TestContext context;
    private final PatientSearchPage searchPage = new PatientSearchPage(DriverFactory.getDriver());

    public PatientSearchSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the patient search page")
    public void i_am_on_the_patient_search_page() {
        DriverFactory.getDriver().get(ConfigReader.baseUiUrl() + "/coreapps/findpatient/findPatient.page?app=coreapps.findPatient");
    }

    @When("I search for the patient by name")
    public void i_search_for_the_patient_by_name() {
        searchPage.searchFor(context.getFullName());
    }

    @When("I search for a patient named {string}")
    public void i_search_for_a_patient_named(String name) {
        searchPage.searchFor(name);
    }

    @Then("the patient should appear in the search results")
    public void the_patient_should_appear_in_the_search_results() {
        assertTrue(searchPage.isPatientListedInResults(context.getFullName()),
                "Expected patient " + context.getFullName() + " to appear in search results");
    }

    @Then("no matching patients should be found")
    public void no_matching_patients_should_be_found() {
        assertFalse(searchPage.getResultRowsText().size() > 0,
                "Expected no search results but some were found");
    }
}
