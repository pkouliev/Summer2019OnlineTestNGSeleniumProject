package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ExplicitWaitPractice {

    private WebDriver driver;
    private Wait<WebDriver> wait;
    private Wait<WebDriver> wait2;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");

        // explicit wait
        // we create object of WebDriver to apply explicit wait
        // we must provide WebDriver object (driver) and period of time
        // within this period of time selenium will check every 500 milliseconds
        wait = new WebDriverWait(driver, 15);
        driver.findElement(By.linkText("Dynamic Loading")).click();
    }

    @Test
    public void test1() {
        // select Example # 1
        // xpath locator alternative: //a[contains(text(),'Example 2')]
        driver.findElement(By.partialLinkText("Example 1")).click();
        // click on start button
        driver.findElement(By.cssSelector("#start > button")).click();

        // we find element first
        WebElement userNameInputBox = driver.findElement(By.id("username"));

        // how to apply condition | ExpectedConditions.condition
        // the problem is element can be present but not visible
        // in this eample, selenium webdriver will wait up to 10 seconds,
        // until element is visible
        // if wait time expire, your test will fail and throw (due to) exception
        // if condition is true, if condition has met, no need to wait longer
        // your script will continue executing
        wait.until(ExpectedConditions.visibilityOf(userNameInputBox));

        //enter username
        userNameInputBox.sendKeys("tomsmith");

        WebElement passwordInputBox = driver.findElement(By.id("pwd"));
        wait.until(ExpectedConditions.visibilityOf(passwordInputBox));
        passwordInputBox.sendKeys("SuperSecretPassword");

        // this is a webbelement that represents submit button
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
        // wait, within 10 seconds, until that button is avaialble for click
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();

        WebElement message = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(message));

        String expectedMessage = "Welcome to the Secure Area. When you are done click logout below.";
        String actualMessage = message.getText();

        Assert.assertEquals(actualMessage, expectedMessage, "Test failed");
        System.out.println("Test passed. Message: " + actualMessage);
    }

    @Test
    public void test2() {
        driver.findElement(By.partialLinkText("Example 5")).click();
        WebElement overlayScreen = driver.findElement(By.cssSelector("[class='fa fa-cog fa-spin']"));

        // wait until overlay screen disappear
        // otherwise, we will have issue to click or enter text
        wait.until(ExpectedConditions.invisibilityOf(overlayScreen));

        // find webelement of user input box
        WebElement userNameInputBox = driver.findElement(By.id("username"));
        // wait until that user input box will be visible
        wait.until(ExpectedConditions.visibilityOf(userNameInputBox));
        // enter user name
        userNameInputBox.sendKeys("tomsmith");

        // find webelement of password input box
        WebElement passwordInputBox = driver.findElement(By.id("pwd"));
        // wait for password input box to become visible
        wait.until(ExpectedConditions.visibilityOf(passwordInputBox));
        passwordInputBox.sendKeys("SuperSecretPassword");


        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();

        WebElement message = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(message));

        String expectedMessage = "Welcome to the Secure Area. When you are done click logout below.";
        String actualMessage = message.getText();

        Assert.assertEquals(actualMessage, expectedMessage, "Test failed");
        System.out.println("Test passed. Message: " + actualMessage);
    }

    @Test(description = "FluentWait example")
    public void test3() {
        driver.findElement(By.partialLinkText("Example 2")).click();
        driver.findElement(By.tagName("button")).click();


        wait2 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class);


        WebElement finish = driver.findElement(By.id("finish"));

        WebElement message = wait2.until(driver -> finish);

        System.out.println(message.getText());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
