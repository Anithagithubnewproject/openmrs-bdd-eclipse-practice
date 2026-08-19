package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("input[type='submit']");
    private final By locationList = By.cssSelector("#sessionLocation li");
    private final By firstLocationOption = By.cssSelector("#sessionLocation li:first-child");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public void submitLogin() {
        if (isDisplayed(locationList)) {
            click(firstLocationOption);
        }
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitLogin();
    }

    public boolean isHomePageDisplayed() {
        return isDisplayed(By.cssSelector("li.nav-item.logout"));
    }
}
