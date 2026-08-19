package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class PatientSearchPage extends BasePage {

    private final By searchBox = By.id("patient-search");
    private final By searchResultRows = By.cssSelector("#patient-search-results-table tbody tr");

    public PatientSearchPage(WebDriver driver) {
        super(driver);
    }

    public PatientSearchPage searchFor(String patientName) {
        type(searchBox, patientName);
        wait.until(d -> {
            String tableText = d.findElement(By.id("patient-search-results-table")).getText();
            return tableText.contains(patientName) || tableText.contains("No matching records found");
        });
        return this;
    }

    public List<String> getResultRowsText() {
        StaleElementReferenceException lastFailure = null;
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                return driver.findElements(searchResultRows).stream()
                        .map(el -> el.getText())
                        .filter(text -> !text.contains("No matching records found"))
                        .collect(Collectors.toList());
            } catch (StaleElementReferenceException e) {
                lastFailure = e;
            }
        }
        throw new IllegalStateException("Result rows kept going stale after retries", lastFailure);
    }

    public boolean isPatientListedInResults(String fullName) {
        try {
            return wait.until(d -> getResultRowsText().stream().anyMatch(row -> row.contains(fullName)));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
