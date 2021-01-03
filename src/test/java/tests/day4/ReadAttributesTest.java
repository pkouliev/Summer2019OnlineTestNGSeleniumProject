package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class ReadAttributesTest {

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(2);

        WebElement inputBox = driver.findElement(By.name("email"));

        // to read value of any attribute
        // name = "email" name is an attribute, email is a value of the attribute
        System.out.println(inputBox.getAttribute("name"));

        inputBox.sendKeys("random_email@email.com");
        // how to read entered text?
        // it's gonna be inside value attribute
        System.out.println(inputBox.getAttribute("value"));

        // if button has a type "submit"
        // we can use .submit() method instead of click() as well
        WebElement passwordButton = driver.findElement(By.id("form_submit"));
        passwordButton.submit();
        // alternative to click(),
        // only if there is attribute type with value "submit", type="submit"

        driver.quit();
    }
}
