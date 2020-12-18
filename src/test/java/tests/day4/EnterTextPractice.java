package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class EnterTextPractice {

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(2);

        WebElement inputBox = driver.findElement(By.name("email"));

        // we enter text
        inputBox.sendKeys("random@email.com");

        WebElement button = driver.findElement(By.id("form_submit"));
        button.click();
        BrowserUtils.wait(2);

        driver.quit();
    }
}
