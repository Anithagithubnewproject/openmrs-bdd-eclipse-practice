package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PatientRegistrationPage extends BasePage {

    private final By givenNameField = By.name("givenName");
    private final By familyNameField = By.name("familyName");
    private final By genderSelect = By.id("gender-field");
    private final By birthdateDayField = By.id("birthdateDay-field");
    private final By birthdateMonthSelect = By.id("birthdateMonth-field");
    private final By birthdateYearField = By.id("birthdateYear-field");
    private final By nextButton = By.id("next-button");
    private final By confirmButton = By.id("submit");
    private final By address1Field = By.id("address1");

    public PatientRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public PatientRegistrationPage enterGivenName(String givenName) {
        type(givenNameField, givenName);
        return this;
    }

    public PatientRegistrationPage enterFamilyName(String familyName) {
        type(familyNameField, familyName);
        return this;
    }

    public PatientRegistrationPage selectGender(String gender) {
        click(nextButton); // Name -> Gender
        new Select(visible(genderSelect)).selectByValue("male".equalsIgnoreCase(gender) ? "M" : "F");
        return this;
    }

    public PatientRegistrationPage enterBirthdate(String birthdate) {
        click(nextButton); // Gender -> Birthdate
        String[] parts = birthdate.split("-"); // "1985-11-02" -> [1985, 11, 02]
        String year = parts[0];
        String month = String.valueOf(Integer.parseInt(parts[1])); // strip leading zero for <option value>
        String day = String.valueOf(Integer.parseInt(parts[2]));
        type(birthdateYearField, year);
        new Select(visible(birthdateMonthSelect)).selectByValue(month);
        type(birthdateDayField, day);
        return this;
    }

    public void submitRegistration() {
        click(nextButton); // Birthdate -> Contact Info (Address)
        type(address1Field, "123 Test Street");
        click(nextButton); // Address -> Phone Number
        click(nextButton); // Phone Number -> Relationships
        click(nextButton); // Relationships -> Confirm
        click(confirmButton); // Confirm -> save
    }

    public boolean isRegistrationConfirmed() {
        return isDisplayed(By.cssSelector(".patient-header, #patient-header"));
    }
}
