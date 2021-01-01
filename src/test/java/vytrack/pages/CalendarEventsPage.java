package vytrack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BrowserUtils;

public class CalendarEventsPage extends LoginPage {

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    public void clickToCreateCalendarEvent() {
        //BrowserUtils.waitForVisibility(createCalendarEvent, 5);
        BrowserUtils.waitForClickability(createCalendarEvent, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(createCalendarEvent, createCalendarEvent.getText()));
        createCalendarEvent.click();
        waitUntilLoaderMaskDisappear();
    }
}
