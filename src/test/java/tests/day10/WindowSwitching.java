package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.Set;

public class WindowSwitching {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);
    }

    @Test(description = "Verify that title is still Practice")
    public void test1() {
        driver.findElement(By.linkText("New tab")).click();
        // after 3 seconds, another website will be opened in the second window
        // selenium doesn't switch automatically to the new window
        BrowserUtils.wait(4);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Practice", "Title is wrong");
        System.out.println("Test passed. Title is verified");

    }

    @Test(description = "Verify that user is able to see new window")
    public void test2() {
        driver.findElement(By.linkText("New tab")).click();
        // record id of original window, that we opened initially
        String oldWindow = driver.getWindowHandle();
        BrowserUtils.wait(4);

        // in the selenium every window has an id. That id is called window handle.
        // To read window handle we use method getWindowHandle()
        // After new window was opened, we can get list of all
        // window id's/ window handles
        // List - it's a data structure
        // Set it's also a data structure like list,
        // but it doesn't allow duplicates
        // Also, you cannot easily access anything from Set
        // there is no get() method due to lack of indexes
        // that's why, we need to loop through the Set, to read data from Set
        // Set can store only unique values
        Set<String> windowHandles = driver.getWindowHandles();
        // loop through the collection of window handles
        for (String windowHandle : windowHandles) {
            // if it's not an old window
            if (!windowHandle.equals(oldWindow)) {
                // switch to that window
                driver.switchTo().window(windowHandle);
            }
        }
        // let's verify that title of new window is Fresh tab
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Fresh tab",
                "Title is wrong");

        // comeback to original page
        // we can build a function, that will jump in between windows based on page title
        // based on page title, we can determine where to stop
        String pageTitle = "Practice"; // title of the original page that we want
//        for(String windowHandle : windowHandles) {
//            // keep switching from window to window
//            driver.switchTo().window(windowHandle);
//            // once the page title of the window matches what we need
//            if (driver.getTitle().equals(pageTitle)) {
//                // just exit the loop
//                // stop switching
//                break;
//            }
//        }
        driver.switchTo().window(oldWindow);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), pageTitle, "Incorrect title");
    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }


}
