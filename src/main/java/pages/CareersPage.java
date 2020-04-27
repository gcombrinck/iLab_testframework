package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.HelperMethods;

public class CareersPage extends HelperMethods{
    public WebDriver driver;

    public CareersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getCareersPageTitle() {
        return driver.getTitle();
    }

    public CountryPage gotoCountryPage(String country) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        waitForElementClickable(By.linkText(country));
        driver.findElement(By.linkText(country)).click();
        return new CountryPage(driver);
    }
}
