package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtils extends Driver {
    public static WebDriver driver = getDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(css = "div[class='loader-mask shown']")
    public static WebElement loaderMask;

    public BrowserUtils() {
        // this method requires to provide WebDriver object for @FindBy
        PageFactory.initElements(driver, this);
    }

    // This method will be used to pause our test execution
    // provide number of seconds as a parameters
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void space() {

        System.out.println("\n================================================================================================\n");
    }

    /**
     * @param element web element
     */
    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            try {
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st) {
                System.out.println(st.getMessage());

            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            y++;
        }
    }

    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element         page elements
     * @param timeToWaitInSec time in seconds
     * @return element once visible
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element page element needs to be clicked
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Waits for provided element to be clickable
     *
     * @param element page element
     * @param timeout time to wait
     * @return element once clickable
     */
    public static WebElement waitForClickability(WebElement element, int timeout) {
        wait = new WebDriverWait(getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * takes screenshot
     * whenever you call this method
     * it takes screenshot and returns location of the screenshot
     *
     * @param name for target file whatever you like
     * @return a path to screenshot takes
     */
    public String getScreenshot(String name) {
        // name the screenshot with the current date to avoid duplicate name
        // String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // System.getProperty("user.dir") returns path to the project as a string
        SimpleDateFormat df = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
        String date = df.format(new Date());

        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // full path to the screenshot location
        // where screenshot will be stored
        // System
        String target = System.getProperty("user.dir") + "/test-output/Screenshots" + name + date + ".png";

        // if it doesn't take screenshot in anyway, remove date and time part
        // for some users it makes problems
        String target2 = System.getProperty("user.dir") + "/test-output/Screenshots" + name + ".png";

        File finalDestination = new File(target);

        // save the screenshot to the path given
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * While this loading screen present, html code is not complete
     * Some elements can be missing
     * Also, you won't be able to interact with any elements
     * All actions will be intercepted
     * Waits until loader mask (loading bar, spinning wheel) disappears
     * <p>
     * // @return true if loader mask is gone, false if it something went wrong
     */
    public static void waitUntilLoaderMaskDisappear() {

        if (driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size() > 0) {

            //loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        }
//
//        try {
//            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
//            return true;
//        } catch (NoSuchElementException e) {
//            System.out.println("Loader mask not found");
//            System.out.println(e.getMessage());
//            return true; // no loader mask, all good, return true
//        } catch (WebDriverException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
    }

}
