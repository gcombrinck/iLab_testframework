package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperMethods;

public class JobPage extends HelperMethods {
    public WebDriver driver;
    private By applyButton = By.className("wpjb-form-job-apply");
    private By applicationForm = By.id("wpjb-apply-form");
    private By nameInput = By.id("applicant_name");
    private By emailInput = By.id("email");
    private By phoneNumberInput = By.id("phone");
    private By submitButton = By.id("wpjb_submit");
    private By errorMessage = By.cssSelector(".wpjb-errors li");

    public JobPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickApplyButton() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        scrollToElement(applyButton);
        waitForElementClickable(applyButton);
        driver.findElement(applyButton).click();
    }

    public void scrollToApplicationForm() {
        WebElement element = driver.findElement(applicationForm);
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void completeJobApplication(String name, String email, String phoneNumber) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        waitForElementVisibilty(nameInput);
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
        driver.findElement(submitButton).click();
    }

    public String getErrorMessage() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        waitForElementVisibilty(errorMessage);
       return driver.findElement(errorMessage).getText();
    }
}
