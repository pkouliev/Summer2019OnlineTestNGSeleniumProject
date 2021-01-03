package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.concurrent.TimeUnit;

public class DragAndDropPractice {

    @Test(description = "Drag and drop example")
    public void test1() {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");

        BrowserUtils.wait(3);
        WebElement cookie = driver.findElement(By.xpath("//*[text()='Accept Cookies']"));
        // click accept cookies
        cookie.click();

        Actions actions = new Actions(driver);
        // moon - it's a draggable object
        // earth - target, where we want to drop draggable object

        WebElement moon = driver.findElement(By.id("draggable"));
        WebElement earth = driver.findElement(By.id("droptarget"));

        BrowserUtils.wait(3); // for demo
        /*
        @param source - element to emulate button down at.
        @param target - element to move to and release the mouse at.
         */
        actions.dragAndDrop(moon, earth).perform();
        BrowserUtils.wait(3); // wait for demo

        driver.quit();

    }
}
