package tests.day6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class BitrixLogin {

    /*
        Login: helpdesk59@cybertekschool.com
        Password: UserUser
     */

    public static void main(String[] args) {

        // getDriver() method returns object of webdriver
        // we need webdriver object to send commands to the browser
        // left side is a reference variable to reuse this object
        // object can be created and used without reference variable,
        // but it's gonna be impossible to use that object more than once
        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("https://login1.nextbasecrm.com/");
        BrowserUtils.wait(2);

        // enter email
        // Webelement email = driver.findElement(");
        // email.sendKeys("");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("helpdesk59@cybertekschool.com");

        // enter password
        // * - matches any element
        // it's better to specify tag name to avoid issues with finding element
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("UserUser");

        // click on login button
        driver.findElement(By.xpath("//*[starts-with(@value, 'Log')]")).click();


        BrowserUtils.wait(2);
        driver.quit();


    }
}
