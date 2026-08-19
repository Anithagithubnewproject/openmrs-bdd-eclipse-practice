package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VisitPage extends BasePage {

    private final By startVisitButton = By.linkText("Start Visit");
    private final By startVisitConfirmButton = By.id("start-visit-with-visittype-confirm");
    private final By activeVisitBanner = By.cssSelector(".active-visit-started-at-message");
    private final By endVisitButton = By.linkText("End Visit");
    private final By endVisitConfirmButton = By.cssSelector("#end-visit-dialog button.confirm");

    public VisitPage(WebDriver driver) {
        super(driver);
    }

    public void startVisit() {
        click(startVisitButton);
        click(startVisitConfirmButton);
    }

    public boolean isVisitActive() {
        return isDisplayed(activeVisitBanner);
    }

    public void endVisit() {
        click(endVisitButton);
        click(endVisitConfirmButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(activeVisitBanner));
    }
}
