package tests.day8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.List;

public class Dropdowns {

    private WebDriver driver;

    /*
    <select id="dropdown">
      <option value="" disabled="disabled" selected="selected">
      Please select an option</option>
      <option value="1">Option 1</option>
      <option value="2">Option 2</option>
    </select>
     */

    @BeforeMethod
    public void setup() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        BrowserUtils.wait(1);
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);

        // <a href="/dropdown">Dropdown</a>
        driver.findElement(By.linkText("Dropdown")).click();
        BrowserUtils.wait(1);
    }

    @Test(description = "Select option 2 from the dropdown")
    public void test1() {
        // to work with select dropdowns,
        // we need to use Select class in Selenium

        // step 1. Find dropdown and create a webelement
        WebElement dropdown = driver.findElement(By.id("dropdown"));

        // step 2. Create a select object
        // Select class requires WebElement object as a parameter
        Select select = new Select(dropdown);

        // to select any option by visible text:
        // also you can select by value or index
        // <option value="2">Option 2</option>
        //        value is 2, Option 2 is a visible text, in between >Text<
        // >Text< - in between angled brackets
        String expectedOption = "Option 2";
        select.selectByVisibleText(expectedOption);
        BrowserUtils.wait(2);

        // verify that option 2 is selected
        // select.getFirstSelectedOption() - to get first selected option
        // this is what selected as option 2
        String actualOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption, expectedOption, "Wrong option");
    }

    @Test(description = "Print list of countries")
    public void test2() {
        WebElement state = driver.findElement(By.id("state"));
        Select select = new Select(state);

        // getOptions() will return available options to select
        List<WebElement> states = select.getOptions();

        // print every option as a text one by one
        for (WebElement eachState : states) {
            System.out.println(eachState.getText() + "\n");
        }
    }

    @Test(description = "Select your state and " +
            "verify that your state is selected ")
    public void test3() {
        WebElement state = driver.findElement(By.id("state"));
        Select select = new Select(state);
        String expectedState = "New York";

        // <option value="NY">New York</option>
        // we can use text, value or index for selection
        select.selectByValue("NY");
        BrowserUtils.wait(2);
        String actualState = select.getFirstSelectedOption().getText();
        Assert.assertEquals(actualState, expectedState,
                "Not my state");
    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}
