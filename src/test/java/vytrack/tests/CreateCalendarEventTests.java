package vytrack.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;
import vytrack.pages.CalendarEventsPage;
import vytrack.pages.CreateCalendarEventPage;
import vytrack.pages.LoginPage;

public class CreateCalendarEventTests extends TestBase {

    @Test(description = "Verify owners name is equals to Pearl Wuckert (it works on qa1 storemanager85)")
    public void test1() {
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        CreateCalendarEventPage createCalendarEventPage = new CreateCalendarEventPage();

        //login as Stephan Haley (storemanager85)
        loginPage.loginQA1();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

        //go to calendar events page
        loginPage.navigateTo("Activities", "Calendar Events");

        //click on create calendar event button
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        calendarEventsPage.clickToCreateCalendarEvent();

        calendarEventsPage.waitUntilLoaderMaskDisappear();
        String expectedOwner = "Stephan Tremaine Haley";
        String actualOwner = createCalendarEventPage.getOwnerName(expectedOwner);

        Assert.assertEquals(actualOwner, expectedOwner);


    }
}
