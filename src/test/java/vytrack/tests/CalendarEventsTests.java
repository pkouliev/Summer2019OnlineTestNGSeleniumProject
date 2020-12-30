package vytrack.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.concurrent.TimeUnit;

/*
Test data:

https://qa1.vytrack.com/
username: storemanager85
password: UserUser123

Test case #1

    1. Go to https://qa1.vytrack.com/
    2. Login as a store manager
    3. Navigate to Activities -> Calendar Events
    4. Verify that page subtitle "All Calendar Events" is displayed

 */
public class CalendarEventsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.id("prependedInput")).
                sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).
                sendKeys("UserUser123", Keys.ENTER);

        WebElement loaderMask = null;

        if (driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size() > 0) {

            loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        }

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement
                (By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
    }

    @Test(description = "Verify page subtitle")
    public void test1() {
        String expectedSubtitle = "All Calendar Events";
        String actualSubtitle = driver.findElement(By.className("oro-subtitle")).getText();
        Assert.assertEquals(actualSubtitle, expectedSubtitle, "Subtitle is wrong!");
    }

    @Test(description = "Verify that 'Create Calendar Event' button is displayed")
    public void test2() {
        WebElement createCalendarEvent = driver.findElement(By.cssSelector("[title='Create Calendar event']"));
        wait.until(ExpectedConditions.visibilityOf(createCalendarEvent));
        Assert.assertTrue(createCalendarEvent.isDisplayed());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
