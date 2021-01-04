package vytrack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;
import utils.Driver;

public class VehiclesPage extends BasePage {

    @FindBy(xpath = "//*[@title='Create Car']")
    public WebElement createCarElement;

    /**
     * Use this method to click on "Create Car" button
     * Method already contains waits to handle synchronization issues
     */
    public void clickToCreateCar() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        BrowserUtils.waitForVisibility(createCarElement, 15);
        BrowserUtils.waitForClickablility(createCarElement, 15);
        wait.until(ExpectedConditions.textToBePresentInElement(createCarElement, "Create Car"));
        createCarElement.click();
    }
}
