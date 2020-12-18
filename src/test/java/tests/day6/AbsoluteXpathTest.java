package tests.day6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;

public class AbsoluteXpathTest {

    public static void main(String[] args) {

        WebDriver driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("https://login1.nextbasecrm.com/");
        BrowserUtils.wait(2);

        // click login without entering username and password
        // to invoke warning message
        driver.findElement(By.className("login-btn")).submit();
        BrowserUtils.wait(2);

        WebElement warningMessage = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div[2]"));
        //WebElement warningMessage = driver.findElement(By.xpath("//div[@class='errortext']"));
        System.out.println(warningMessage.getText()); // to read the text of warning message

        BrowserUtils.wait(2);
        driver.quit();


    }
}
