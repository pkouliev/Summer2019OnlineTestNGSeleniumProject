package vytrack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

public class CreateCalendarEventPage extends BasePage {

    @FindBy(css = "[class='select2-chosen']")
    private WebElement owner;

    public String getOwnerName(String ownerName) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.textToBePresentInElement(owner, ownerName));
        return owner.getText().trim();
    }
}
