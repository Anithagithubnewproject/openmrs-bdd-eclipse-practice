package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

import static org.testng.Assert.assertTrue;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    
    
    @Given("I am logged into OpenMRS")
    public void i_am_logged_into_openmrs() {
        loginPage.open(ConfigReader.baseUiUrl());
        loginPage.login(ConfigReader.username(), ConfigReader.password());
        // Login submits, but the session location isn't necessarily committed
        // server-side the instant the button click returns. Steps that immediately
        // navigate elsewhere (registration/search/visit dashboards) can race that and
        // get bounced back to the home page - wait for login to genuinely land first.
        assertTrue(loginPage.isHomePageDisplayed(), "Login did not complete - home page never displayed");
    }


    @Given("I am on the OpenMRS login page")
    public void i_am_on_the_openmrs_login_page() {
        loginPage.open(ConfigReader.baseUiUrl());
    }

    @When("I log in with valid credentials")
    public void i_log_in_with_valid_credentials() {
        loginPage.login(ConfigReader.username(), ConfigReader.password());
    }

    @Then("I should land on the home page")
    public void i_should_land_on_the_home_page() {
        assertTrue(loginPage.isHomePageDisplayed(), "Expected to land on the home page after login");
    }
}
