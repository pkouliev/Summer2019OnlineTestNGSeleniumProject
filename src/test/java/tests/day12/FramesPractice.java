package tests.day12;

import org.openqa.selenium.By;
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

public class FramesPractice {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");
        wait = new WebDriverWait(driver, 15);
        driver.findElement(By.linkText("Frames")).click();
    }

    @Test(description = "iFrame example")
    public void test1() {
        driver.findElement(By.linkText("iFrame")).click();

        // since element is inside the frame, the element is not visible for selenium
        // without switching to the frame
        // we can switch to the frame based on id, name,
        // index(starting from 0), web element
        driver.switchTo().frame("mce_0_ifr");
        // WITHOUT SWITCHING, WE CANNOT SEE INNER HTML DOCUMENT
        // which one to use? id, name, index, webbelement?
        // 1. id or name <iframe id="mce_0_ifr" name="some_frame">
        // 2. webelement driver.findElement(By.cssSelector("iframe[class='some_frame']"));
        // 3. index [iframe1, igrame2, iframe3...]


        WebElement inputArea = driver.findElement(By.id("tinymce"));
        wait.until(ExpectedConditions.visibilityOf(inputArea));

        String expectedText = "Your content goes here.";
        String actualText = inputArea.getText();
        Assert.assertEquals(actualText, expectedText);
        System.out.println(actualText);
        BrowserUtils.wait(2);

        inputArea.clear(); // to delete text

        BrowserUtils.wait(2);

        inputArea.sendKeys("Java is fun!");

        // to exit from the frame
        driver.switchTo().defaultContent();
//        WebElement header = driver.findElement(By.tagName("h3"));
//        wait.until(ExpectedConditions.elementToBeClickable(header));
//        String expectedHeader = "Frames";
//        String actualHeader = header.getText();
//        Assert.assertEquals(actualHeader, expectedHeader);
//        System.out.println(actualHeader);
    }

    // in case of nested frames
    // we must switch to first frame --> then again to another frame, that is inside
    // -- html
    // --- frame #1
    // ---- frame #2
    @Test(description = "Nested Frames example")
    public void test2() {
        // it's not switch to frame
        // it's a navigation action
        driver.findElement(By.linkText("Nested Frames")).click();
        // we switch to frame based on webbelement
        driver.switchTo().frame(driver.findElement(By.cssSelector("[name='frame-bottom']")));
        // the reason why we are switching here
        // is because content that is inside a frame is not visible for selenium
        // it's like when you are on the first floor
        // trying to find what is on the second floor
        WebElement content = driver.findElement(By.tagName("body"));
        System.out.println(content.getText());

        driver.switchTo().defaultContent(); // to eit from all frames

        driver.switchTo().frame("frame-top"); // second floor
        driver.switchTo().frame("frame-left"); // thirt floor

        System.out.println(driver.findElement(By.tagName("body")).getText()); // print text of body


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
