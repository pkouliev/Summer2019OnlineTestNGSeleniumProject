package vytrack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateCalendarEventPage extends CalendarEventsPage {

    @FindBy(css = "[class='select2-chosen']")
    private WebElement owner;

    public String getOwnerName(String ownerName) {
        wait.until(ExpectedConditions.textToBePresentInElement(owner, ownerName));
        return owner.getText();
    }
}
