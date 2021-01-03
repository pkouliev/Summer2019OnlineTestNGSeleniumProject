package tests.day7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestNG_Practice {

    @Test
    public void test() {

        // to verify that expected and actual result is the same
        // if no - it will throw exception and stop the program
        // also, you will see in the console what was expected
        // and what was actually
        // click and hold command button and
        // use left click/single click to open the class
        // then on the right top side,
        // you will see "download sources" message
        // click on it
        Assert.assertEquals("apple", "apple",
                "Word is not correct should be apple");
    }

    @Test
    public WebDriver openSite() {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(1);

        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);

        return driver;
    }

    @Test(description = "Verifying title on the practice website")
    public void verifyTitle() {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(1);

        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);

        String expectedTitle = "Practice";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "Title is wrong");


        BrowserUtils.wait(1);
        driver.quit();

    }

    // let's verify that Test Automation Practice heading is displayed

    @Test(description = "Verify that heading is displayed")
    public void verifyHeadingIsDisplayed() {

        WebDriver driver = openSite();

        //span[text()='Test Automation Practice']

        // if there is no element with this locator, we will get NoSuchElementException
        // and our program will stop on the findElement line
        WebElement heading = driver.findElement(By.xpath("//span[text()='Test Automation Practice']"));

        // to make sure that element is visible to user
        // because element can be present, but not visible
        // we need to make sure element is visible
        // Element is not visible message will be printed if element is there, but not visible
        // assertTrue - method tat cheks if something is true
        // if it's false, you will get exception
        // .isDisplayed() return true or false
        // if it returns true - that means element is visible
        // if this method returns false - element is not visible
        Assert.assertTrue(heading.isDisplayed(), "Element is not visible");

        BrowserUtils.wait(1);
        driver.quit();

    }
}
