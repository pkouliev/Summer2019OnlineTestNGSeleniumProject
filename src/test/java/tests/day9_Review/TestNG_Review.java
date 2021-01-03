package tests.day9_Review;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestNG_Review {
    // whatever is common among tests, can go into @BeforeMethod and @AfterMethod
    // it helps to reduce code duplication

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        BrowserUtils.space();
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.wait(1);
        assert driver != null;
        driver.manage().window().maximize();
    }

    // priority will change the order of test execution
    // by default tests are runnning in alphabetic order
    // first priority - earlier execution
    // lower priority number - higher priority of execution
    // tests will run like: priority 1, priority2, priority 3...
    // default priority is 0
    @Test(description = "Verify title of google.com", priority = 2)
    public void test1() {
        System.out.println("Test1: Verify title of google.com");
        driver.get("http://google.com");
        BrowserUtils.wait(1);
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle,
                "Title is not correct");
        // if assertion failed, line below will not be reachable
        // if test passed, you will see message from line below
        System.out.println("Test passed!");
    }

    @Test(description = "Verify title of apple.com", priority = 1)
    public void test2() {
        System.out.println("Test 2: Verify title of apple.com");
        driver.get("https://www.apple.com/");
        String expectedTitle = "Apple";
        String actualTitle = driver.getTitle();
        // message will be printed if assertion failed
        Assert.assertEquals(actualTitle, expectedTitle,
                "Title is not correct");
        System.out.println("Test passed!");
    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}
