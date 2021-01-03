package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionsClassHoverPractice {

    private WebDriver driver;
    private Actions action;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");
        driver.findElement(By.linkText("Hovers")).click();
        action = new Actions(driver);
    }

    @Test(description = "Verify first image")
    public void test1() {
        // create object of actions class to perform actions (drag and drop,
        // context click, move to specific point, etc...)
        // or [class='figure']:nth-of-type(1) nth means 8th, 7th, 4th child....
        WebElement image1 = driver.findElement(By.cssSelector(".figure:nth-of-type(1)"));
        // just to hover on element, not click
        // build() is required when we have more than 1 action in a chain
        // without perform() it will not work
        // moveToElement() = hovering
        // perform() stands for triggering actions
        action.moveToElement(image1).perform();

        BrowserUtils.wait(3); // wait for demo
        // h5 is a grand child of .figure:nth-of-type(1),
        // this element contains image
        WebElement textOfImageElement = driver.findElement(By.cssSelector
                (".figure:nth-of-type(1) h5"));

        String expectedText = "name: user1";
        String actualText = textOfImageElement.getText();
        Assert.assertEquals(actualText, expectedText);

        System.out.println(actualText);
    }

    @Test(description = "Verify all images (forEach loop)")
    public void test2() {
        List<WebElement> images = driver.findElements(By.cssSelector("[class='figure'] img"));
        List<WebElement> textOfImage = driver.findElements(By.tagName("h5"));

        int i = 0;
        for (WebElement image : images) {

            action.moveToElement(image).perform();
            BrowserUtils.wait(3);

            String expectedImageText = "name: user" + (i + 1);
            String actualImageText = textOfImage.get(i).getText();
            Assert.assertEquals(actualImageText, expectedImageText);
            System.out.println(actualImageText);
            i++;
        }
    }

    @Test(description = "Verify all images (for loop)")
    public void test3() {
        for (int i = 1; i <= 3; i++) {
            action.moveToElement(driver.findElement(By.cssSelector
                    (".figure:nth-of-type(" + i + ")"))).perform();
            BrowserUtils.wait(3);
            String name = driver.findElement(By.cssSelector
                    (".figure:nth-of-type(" + i + ") h5")).getText();
            System.out.println(name);
            Assert.assertEquals(name, "name: user" + i);
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
