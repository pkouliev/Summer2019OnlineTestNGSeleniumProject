package tests.day8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.List;

public class RadioButtons {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        BrowserUtils.wait(1);
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);

        // to go to Radio Buttons page
        // <a href="/radio_buttons">Radio Buttons</a>
        // linkText locator works only with <a> elements
        // link text only in between >Text<
        // this step is common for all test cases
        driver.findElement(By.linkText("Radio Buttons")).click();
        BrowserUtils.wait(1);
    }

    @Test(description = "Verify that blue button is selected")
    public void test1() {

        // find blue radio button
        WebElement blueButton = driver.findElement(By.id("blue"));

        // let's verify that radio button is selected
        // assert true that button is selected
        // if button is selcted it will return true, otherwise false
        boolean isSelected = blueButton.isSelected();
        Assert.assertTrue(isSelected, "Blue button is not selected");
        // assertTrue will expect isSelected is true
    }

    @Test(description = "Verify that red button is not selected")
    public void test2() {
        WebElement redButton = driver.findElement(By.id("red"));
        // isSelected() will return true if button is already clicked
        Assert.assertFalse(redButton.isSelected(), "Red button is selected");
        // assertFalse will expect isSelected is false

    }

    @Test(description = "Verify that green button is not clickable")
    public void test3() {
        WebElement greenButton = driver.findElement(By.id("green"));
        // isEnabled() will return true if button is avaialble for interaction
        // that means you can click on it, in this case
        Assert.assertFalse(greenButton.isEnabled(), "Green button is clickable");
    }

    // let's find all radio buttons and click on them one by one
    @Test(description = "Click on all radio buttons")
    public void test4() {
        // how to find all radio buttons?
        // find all radio buttons
        // any radio button will have type="radio" and input as a element type
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        // let's click only if button is not clicked and is available for clicking
        for (WebElement button : radioButtons) {
            // if button is available for clicking and not clicked yet
            if (button.isEnabled() && !button.isSelected()) {
                // then click on it
                button.click();
                // in this case, id attribute represents a name of the color
                // also, there is no text in these elements
                // that's why I print attribute value
                // <input type="radio" id="value" name="color">
                // attributes: type       id          name
                System.out.println(button.getAttribute("id") + " button was clicked \n");
                BrowserUtils.wait(2);
            } else {
                System.out.println(button.getAttribute("id") + " button was not clicked \n");
            }
        }

    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}
