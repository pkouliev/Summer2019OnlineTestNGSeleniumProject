package tests.day8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class CheckBoxes {

    // command + option + L - to organize code for Mac
    // control + alt + L - to organize code for windows

    private WebDriver driver;
    // private because it will be used in this class

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        BrowserUtils.wait(1);
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(1);

        // <a href="/checkboxes">Checkboxes</a>
        driver.findElement(By.linkText("Checkboxes")).click();
        BrowserUtils.wait(1);
    }

    @Test
    public void test1() {
        // find all checkboxes
        // any chackbox will have type="checkbox"
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type='checkbox']"));
        int index = 1;
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isEnabled() && !checkbox.isSelected()) {
                checkbox.click();
                BrowserUtils.wait(1);
                System.out.println("Checkbox " + index + " is clicked \n");
            } else {
                System.out.println("Checkbox " + index + " is not clicked \n");
            }
            index++;
        }


    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}




