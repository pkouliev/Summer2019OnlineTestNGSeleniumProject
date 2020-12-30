package vytrack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestBase;

// everything that is in common among pages
// can go here
// for example top menu elements don't belong to specific page
// top menu appears on every single page
// so we can keep them here
public abstract class BasePage extends TestBase {

    @FindBy(css = "div[class='loader-mask shown']")
    public WebElement loaderMask;

    public BasePage() {
        // this method requires to provide WebDriver object for @FindBy and page class, including this class
        PageFactory.initElements(driver, this);
    }

    /**
     * This method stands for navigation in vytrack app
     * provide module name, for example "Fleet" as a String
     * and subModule name, for example "Vehicles"
     * then based on these values, locators will be created
     *
     * @param moduleName    normalize-space() is same action as .trim() in java
     * @param subModuleName normalize-space() is same action as .trim() in java
     */
    public void navigateTo(String moduleName, String subModuleName) {
        String moduleLocator = "//*[normalize-space()='" + moduleName + "' and @class='title title-level-1]";
        String subModuleLocator = "//*[normalize-space()='" + subModuleName + "' and @class='title title-level-2]";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = driver.findElement(By.xpath(moduleLocator));
        wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));
        module.click(); // once we clicked on module, submodule should be visible

        WebElement subModule = driver.findElement(By.xpath(subModuleLocator));
        wait.until(ExpectedConditions.visibilityOf(subModule));
        subModule.click();

        // after navifation wait until loader mask disappear
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
    }

}
