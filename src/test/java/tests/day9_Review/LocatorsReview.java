package tests.day9_Review;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

public class LocatorsReview {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(1);
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);
    }

    // locators: id, name, tag name, class name, linkText,
    // partial linkText
    @Test(description = "Verify checkboxes")
    public void test1() {
        driver.findElement(By.linkText("Checkboxes")).click();
        BrowserUtils.wait(1);

        // [type='checkbox'] - since there are 2 elements with same locator
        // and I need only first one, I can use in cssSelector, :nth-of-type(1)
        // it's very useful if you have more than one element under cssSelector
        // any tag or tag + attributes : :nth-of-type(index)
        // Tip: look at the :nth-child() selector to select the element that is
        // the nth child, regardless of type, of its parent
        WebElement checkbox1 = driver.findElement(By.cssSelector
                ("[type='checkbox']:nth-of-type(1)"));

        // how to check if checkbox is not clicked?
        Assert.assertFalse(checkbox1.isSelected(),
                "Checkbox 1 was selected");
        // assert that checkbox 1 is not selected
        System.out.println("Test passed. Checkbox 1 is not selected");

        // [index] - to specify index of the element,
        // if there are multiple elements with same xpath root
        WebElement checkbox2 = driver.findElement(By.xpath("//*[@type='checkbox'][2]"));
        Assert.assertTrue(checkbox2.isSelected(),
                "Checkbox 2 was not selected");
        System.out.println("Test passed. Checkbox 2 is selected");
        // again, cssSelector is preferable, because of speed.
        // assertion is like if statement
//        if (true) {
//            "test passed"
//        } else {
//            "test failed"
//                    throw new RuntimeException("Expected true but was false")
//        }
    }

    @Test(description = "Radio buttons test")
    public void test2() {
        driver.findElement(By.linkText("Radio Buttons")).click();
        WebElement blueButton = driver.findElement(By.xpath("//*[text()='Blue']/preceding-sibling::input[@type='radio']"));
        Assert.assertTrue(blueButton.isSelected(), "Blue button is not selected");
        System.out.println("Test passed. Blue button is selected");
    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}
