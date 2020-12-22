package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.concurrent.TimeUnit;

public class ImplicitWaitPractice {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        // this line should be before all tests and findElement() methods
        // to wait within 10 seconds until the element is present
        // we apply it once, and it always works
        // in @BeforeMethod it will work for all tests
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");
    }

    @Test
    public void test1() {

        driver.findElement(By.linkText("Dynamic Loading")).click();

        // partialLinkText means we match only part of the link text
        // partialLinkText is like contains text
        // Example 2: Element on the page that is rendered after the trigger - linkText
        // Example 2 - only part of the linkText
        // we can use partialLinkText to find the element by partial text
        driver.findElement(By.partialLinkText("Example 2")).click();

        driver.findElement(By.cssSelector("#start > button")).click();

        // this is for "Hello World!"
        WebElement finishElement = driver.findElement(By.id("finish"));
        String expectedMessage = "Hello World!";
        String actualMessage = finishElement.getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Message don't match");
        System.out.println(finishElement.getText());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
