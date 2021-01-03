package vytrack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import vytrack.pages.CreateCalendarEventPage;

public class CreateCalendarEventTests extends CreateCalendarEventPage {

    @Test(description = "Verify owners name equals to Stephan Tremaine Haley (it works on qa1 storemanager85 ")
    public void test1() {

        // login as Stephan Tremaine Haley (storemanager85)
        loginQA1();

        //go to calendar events page
        navigateTo("Activities", "Calendar Events");

        // click on create calendar event button
        clickToCreateCalendarEvent();

        String expectedOwner = "Stephan Tremaine Haley";
        String actualOwner = getOwnerName(expectedOwner).trim();

        Assert.assertEquals(actualOwner, expectedOwner);
    }
}
