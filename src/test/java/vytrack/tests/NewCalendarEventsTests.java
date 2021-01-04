package vytrack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestBase;
import vytrack.pages.LoginPage;

public class NewCalendarEventsTests extends TestBase {

    @Test(description = "Verify that page subtitle is equals to 'All Calendar Events'")
    public void test1() {
        extentTest = extentReports.createTest("Verify that page subtitle is equals to 'All Calendar Events'");
        LoginPage loginPage = new LoginPage(); //login page object

        loginPage.loginQA1();
        loginPage.navigateTo("Activities", "Calendar Events");

        String expectedSubtitle = "All Calendar Events";
        String actualSubTitle = loginPage.getPageSubTitle(expectedSubtitle);

        Assert.assertEquals(actualSubTitle, expectedSubtitle);
        extentTest.pass("Verified that page subtitle 'All Calendar Events' is displayed");
    }


}

