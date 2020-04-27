package gcombrinck;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.CountryPage;
import pages.JobPage;
import utils.GenerateRandom;
import utils.ReadCSV;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyTest extends BaseTests {
    GenerateRandom generate = new GenerateRandom();

    @Test
    public void ApplyJobWithStaticData() {
        CareersPage careersPage = homePage.gotoCareersPage();
        assertTrue(careersPage.getCareersPageTitle().contains("CAREERS | iLAB"));

        CountryPage countryPage = careersPage.gotoCountryPage("South Africa");
        assertEquals(countryPage.getPageTitle(), "SOUTH AFRICA | iLAB", "Incorrect page title.");

        JobPage jobPage = countryPage.clickJobposting(1);
        jobPage.clickApplyButton();

        String phoneNumber = generate.generateCellNumber();
        jobPage.completeJobApplication("Automation TestUser", "automationAssessment@iLABQuality.com", phoneNumber);
        assertEquals(jobPage.getErrorMessage(), "You need to upload at least one file.", "Incorrect error message.");
    }

    @DataProvider
    public Object[][] getData() {
        String phoneNumber = generate.generateCellNumber();
        return new Object[][]{{"Automation TestUser 2", "automationAssessment@iLABQuality.com", phoneNumber}};
    }

    @Test(dataProvider = "getData")
    public void ApplyJobWithDataProvider(String name, String email, String cellNumber) {
        CareersPage careersPage = homePage.gotoCareersPage();
        assertTrue(careersPage.getCareersPageTitle().contains("CAREERS | iLAB"));

        CountryPage countryPage = careersPage.gotoCountryPage("South Africa");
        assertEquals(countryPage.getPageTitle(), "SOUTH AFRICA | iLAB", "Incorrect page title.");

        JobPage jobPage = countryPage.clickJobposting(1);
        jobPage.clickApplyButton();
        GenerateRandom generate = new GenerateRandom();
        jobPage.completeJobApplication(name, email, cellNumber);
        assertEquals(jobPage.getErrorMessage(), "You need to upload at least one file.", "Incorrect error message.");
    }

    @DataProvider
    public Object[][] csvData() throws IOException {
        String phoneNumber = generate.generateCellNumber();
        ReadCSV readCSV = new ReadCSV();
        List<String[]> data = readCSV.parseCsvData(new FileReader("src/test/java/gcombrinck/data/data.csv"));
        return data.toArray(new Object[0][]);
    }

    @Test(dataProvider = "csvData")
    public void ApplyJobWithCSVDataProvider(String name, String email) {
        CareersPage careersPage = homePage.gotoCareersPage();
        assertTrue(careersPage.getCareersPageTitle().contains("CAREERS | iLAB"));

        CountryPage countryPage = careersPage.gotoCountryPage("South Africa");
        assertEquals(countryPage.getPageTitle(), "SOUTH AFRICA | iLAB", "Incorrect page title.");

        JobPage jobPage = countryPage.clickJobposting(1);
        jobPage.clickApplyButton();
        GenerateRandom generate = new GenerateRandom();
        jobPage.completeJobApplication(name, email, generate.generateCellNumber());
        assertEquals(jobPage.getErrorMessage(), "You need to upload at least one file.", "Incorrect error message.");
    }
}
