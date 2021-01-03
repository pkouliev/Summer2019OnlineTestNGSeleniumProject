package vytrack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import vytrack.pages.CalendarEventsPage;

public class NewCalendarEventsTests extends CalendarEventsPage {

    @Test(description = "Verify that page subtitle equals to 'All Calendar Events'")
    public void test1() {
        // this step is required for every test
        // otherwise you will get nullpointer exception
        // you must create a test at the beginning
        extentTest = extentReports.createTest("Verify that page subtitle equals to 'All Calendar Events'");
        loginQA1();
        navigateTo("Activities", "Calendar Events");

        String expectedSubtitle = "All Calendar Events";
        String actualSubTitle = getPageSubTitle(expectedSubtitle);

        Assert.assertEquals(actualSubTitle, expectedSubtitle, "Test Failed. Subtitles don't match");
        System.out.println("Actual Subtitle: " + actualSubTitle);

        extentTest.pass("Verified that page subtitle 'All Calendar Events' is displayed");
    }
}
