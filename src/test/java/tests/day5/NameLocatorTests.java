package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class NameLocatorTests {

    public static void main(String[] args) {

        // remember!
        // for Mac users: there is no need to use WebDriverManager for Safari
        // Safari has embedded webdriver support.
        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/sign_up");
        BrowserUtils.wait(2);

        // if you want to do in one line without creating webelement reference variable
        // enter full name
        driver.findElement(By.name("full_name")).sendKeys("Test User");
        // enter email
        driver.findElement(By.name("email")).sendKeys("test_email@email.com");
        // click sign up
        driver.findElement(By.name("wooden_spoon")).submit();

        BrowserUtils.wait(2);
        driver.quit();


    }
}
