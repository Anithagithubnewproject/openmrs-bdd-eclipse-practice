package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.VisitPage;
import utils.ConfigReader;
import utils.DriverFactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VisitManagementSteps {

    private final TestContext context;
    private final VisitPage visitPage = new VisitPage(DriverFactory.getDriver());

    public VisitManagementSteps(TestContext context) {
        this.context = context;
    }

    @Given("I have navigated to that patient's dashboard")
    public void i_have_navigated_to_that_patient_s_dashboard() {
        String url = ConfigReader.baseUiUrl() + "/coreapps/clinicianfacing/patient.page?patientId="
                + context.getPatientUuid();
        DriverFactory.getDriver().get(url);
    }

    @Given("the patient has an active visit")
    public void the_patient_has_an_active_visit() {
        if (!visitPage.isVisitActive()) {
            visitPage.startVisit();
        }
    }

    @When("I start a new visit for the patient")
    public void i_start_a_new_visit_for_the_patient() {
        visitPage.startVisit();
    }

    @When("I end the active visit")
    public void i_end_the_active_visit() {
        visitPage.endVisit();
    }

    @Then("the patient should have an active visit")
    public void the_patient_should_have_an_active_visit() {
        assertTrue(visitPage.isVisitActive(), "Expected the patient to have an active visit");
    }

    @Then("the patient should no longer have an active visit")
    public void the_patient_should_no_longer_have_an_active_visit() {
        assertFalse(visitPage.isVisitActive(), "Expected the patient to no longer have an active visit");
    }
}
