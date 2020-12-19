package tests.day7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.BrowserUtils;

public class MulipleButtonsTests {

    private WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        BrowserUtils.wait(1);
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(1);

    }

    @Test
    public void verifyButton1() {
        String expectedResult = "Clicked on button one!";

        driver.findElement(By.xpath("//*[text()='Button 1']")).click();
        BrowserUtils.wait(1);

        String actualResult = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(actualResult, expectedResult, "Result is wrong");

    }

    @Test
    public void verifyButton2() {
        String expectedResult = "Clicked on button two!";

        driver.findElement(By.name("button2")).click();
        BrowserUtils.wait(1);

        String actualResult = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(actualResult, expectedResult, "Result is wrong");
    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}
