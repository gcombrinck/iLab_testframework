package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.HelperMethods;

public class CountryPage extends HelperMethods {
    public WebDriver driver;
    private  By jobList = By.className("wpjb-grid-row");
    private  By jobTitle = By.className("wpjb-job_title");

    public CountryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    /**
     *
     * @param index start at 1
     * @return
     */
    public JobPage clickJobposting(int index) {
        waitForElementClickable(jobList);
        driver.findElements(jobList).get(index - 1).findElement(jobTitle).click();
        return new JobPage(driver);
    }

}
