package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public WebDriver driver;
    private By careersLink = By.linkText("CAREERS");
    private By popupCloseButton = By.cssSelector("#popmake-3738 button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public CareersPage gotoCareersPage() {
        driver.findElement(careersLink).click();
        return new CareersPage(driver);
    }

    public Boolean isPopupDisplayed() {
        try {
             return driver.findElement(popupCloseButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void closePopup() {
        driver.findElement(popupCloseButton).click();
    }
}
