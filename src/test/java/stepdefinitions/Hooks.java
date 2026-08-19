package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {

    @Before(value = "@ui", order = 0)
    public void setUpUiDriver() {
        DriverFactory.getDriver();
    }

    @After(value = "@ui")
    public void tearDownUiDriver(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
