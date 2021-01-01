package vytrack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BrowserUtils;
import utils.TestBase;

import java.util.NoSuchElementException;

// everything that is in common among pages
// can go here
// for example top menu elements don't belong to specific page
// top menu appears on every single page
// so we can keep them here
public abstract class BasePage extends TestBase {

    @FindBy(css = "div[class='loader-mask shown']")
    public WebElement loaderMask;

    @FindBy(xpath = "//*[@class='oro-subtitle']")
    private WebElement pageSubTitle;

    @FindBy(css = "#user-menu>a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;

    public BasePage() {
        // this method requires to provide WebDriver object for @FindBy and page class, including this class
        PageFactory.initElements(driver, this);
    }

    /**
     * While this loading screen present, html code is not complete
     * Some elements can be missing
     * Also, you won't be able to interact with any elements
     * All actions will be intercepted
     * Waits until loader mask (loading bar, spinning wheel) disappears
     *
     * @return true if loader mask is gone, false if it something went wrong
     */
    public boolean waitUntilLoaderMaskDisappear() {

//        if (driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size() > 0) {
//
//            //loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
//            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
//        }

        try {
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found");
            System.out.println(e.getMessage());
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
        return false;
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
        String moduleLocator = "//a[normalize-space()='" + moduleName + "']";
        String subModuleLocator = "//a[normalize-space()='" + subModuleName + "']";

        waitUntilLoaderMaskDisappear();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = driver.findElement(By.xpath(moduleLocator));
        //wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));
        wait.until(ExpectedConditions.textToBePresentInElement(module, moduleName));
        module.click(); // once we clicked on module, submodule should be visible

        WebElement subModule = driver.findElement(By.xpath(subModuleLocator));
        //wait.until(ExpectedConditions.visibilityOf(subModule));
        wait.until(ExpectedConditions.elementToBeClickable(subModule));
        wait.until(ExpectedConditions.textToBePresentInElement(subModule, subModuleName));
        subModule.click();
        waitUntilLoaderMaskDisappear();


        // after navigation wait until loader mask disappear
        // if loader screen is there, then wait for invisibility

//        if (driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size() > 0) {
//
//            loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
//            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
//        }
    }

    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle(String subTitle) {
        wait.until(ExpectedConditions.textToBePresentInElement(pageSubTitle, subTitle));
        return pageSubTitle.getText();
    }

    public String getUserName() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
    }

    public void logOut() {
        BrowserUtils.wait(2);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);
    }

    public void goToMyUser() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForClickability(userName, 5).click();
        BrowserUtils.waitForClickability(myUser, 5).click();
    }

}
