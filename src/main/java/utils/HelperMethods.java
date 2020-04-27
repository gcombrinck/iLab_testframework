package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperMethods {
    public WebDriver driver;

    public HelperMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToElement(By element) {
        WebElement webElement = driver.findElement(element);
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) driver).executeScript(script, webElement);
    }

    public void waitForElementVisibilty(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(element)));
    }

    public void waitForElementClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(element)));
    }
}
