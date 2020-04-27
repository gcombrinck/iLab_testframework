package gcombrinck;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;

    @Parameters("browser")
    @BeforeClass
    public void testUp(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void goHome() {
        driver.get("https://www.ilabquality.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        if (homePage.isPopupDisplayed()) {
            homePage.closePopup();
        }
        assertEquals(homePage.getPageTitle(), "Home Page | iLAB", "Incorrect page title.");
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("screenshots/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}