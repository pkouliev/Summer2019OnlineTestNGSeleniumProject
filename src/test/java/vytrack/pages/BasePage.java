package vytrack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;
import utils.Driver;

import java.util.NoSuchElementException;

//everything that is in common among pages
//can go here
//for example top menu elements don't belong to specific page
//top menu appears on every single page
//so we can keep them here
public class BasePage {

    @FindBy(css = "div[class='loader-mask shown']")
    public WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;

    public BasePage() {
        //this method requires to provide webdriver object for @FindBy
        PageFactory.initElements(Driver.getDriver(), this);
    }

    /**
     * While this loading screen present, html code is a not complete
     * Some elements can be missing
     * Also, you won't be able to interact with any elements
     * All actions will be intercepted
     * Waits until loader mask (loading bar, spinning wheel) disappears
     *
     * @return true if loader mask is gone, false if something went wrong
     */
    public boolean waitUntilLoaderMaskDisappear() {

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loader-mask shown']")));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found!");
            e.printStackTrace();
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method stands for navigation in vytrack app
     * provide tab name, for example "Fleet" as a String
     * and module name, for example "Vehicles" as a String as well
     * then based on these values, locators will be created
     *
     * @param moduleName    provided for each test
     * @param subModuleName taken from submenu of module
     */
    public void navigateTo(String moduleName, String subModuleName) {
        String moduleLocator = "//a[normalize-space()='" + moduleName + "']";
        String subModuleLocator = "//*/span[(text()='" + subModuleName + "')]";

        //waitUntilLoaderMaskDisappear();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = Driver.getDriver().findElement(By.xpath(moduleLocator));
        //wait.until(ExpectedConditions.visibilityOf(module));
        waitUntilLoaderMaskDisappear();
//        wait.until(ExpectedConditions.elementToBeClickable(module));
//        wait.until(ExpectedConditions.textToBePresentInElement(module, moduleName));
//        module.click(); // once we clicked on module, submodule should be visible

        try {
            BrowserUtils.waitForClickablility(Driver.getDriver().findElement(By.xpath(moduleLocator)), 5);
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
            wait.until(ExpectedConditions.textToBePresentInElement(module, moduleName));
            new Actions(Driver.getDriver()).moveToElement(module).pause(200).doubleClick(module).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(moduleLocator), 5);
        }

        WebElement subModule = Driver.getDriver().findElement(By.xpath(subModuleLocator));
//        waitUntilLoaderMaskDisappear();
//        wait.until(ExpectedConditions.visibilityOf(subModule));
//        wait.until(ExpectedConditions.elementToBeClickable(subModule));
//        wait.until(ExpectedConditions.textToBePresentInElement(subModule, subModuleName));
//        subModule.click();
//        waitUntilLoaderMaskDisappear();

        try {
            BrowserUtils.waitForPresenceOfElement(By.xpath(subModuleLocator), 5);
            BrowserUtils.waitForClickablility(Driver.getDriver().findElement(By.xpath(subModuleLocator)), 5);
            BrowserUtils.scrollToElement(Driver.getDriver().findElement(By.xpath(subModuleLocator)));
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
            wait.until(ExpectedConditions.textToBePresentInElement(subModule, subModuleName));
            Driver.getDriver().findElement(By.xpath(subModuleLocator)).click();
        } catch (Exception e) {
            BrowserUtils.waitForStaleElement(Driver.getDriver().findElement(By.xpath(subModuleLocator)));
            BrowserUtils.clickWithTimeOut(Driver.getDriver().findElement(By.xpath(subModuleLocator)), 5);
        }

        // it waits until page is loaded and ajax calls are gone
        BrowserUtils.waitForPageToLoad(5);

    }

    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle(String subTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        waitUntilLoaderMaskDisappear();
        wait.until(ExpectedConditions.textToBePresentInElement(pageSubTitle, subTitle));
        return pageSubTitle.getText().trim();
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
        BrowserUtils.waitForClickablility(userName, 5).click();
        BrowserUtils.waitForClickablility(myUser, 5).click();
    }

    public void waitForPageSubtitle(String pageSubtitleText) {
        new WebDriverWait(Driver.getDriver(), 10).until(ExpectedConditions.textToBe(By.cssSelector("h1[class='oro-subtitle']"), pageSubtitleText));
    }

}

