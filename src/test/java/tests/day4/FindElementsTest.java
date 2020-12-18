package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class FindElementsTest {

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(2);

        // Step 1. Open inspector in chrome and find the locator for the element
        // Step 2. Create object of webElement
        // Step 3. Use webelement
        WebElement button = driver.findElement(By.id("form_submit"));
        // to click on the element
        button.click();

        BrowserUtils.wait(3);

        driver.quit();
    }
}
