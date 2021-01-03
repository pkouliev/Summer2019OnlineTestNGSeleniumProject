package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class FileUploading {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");

    }

    @Test(description = "Verify that file was uploaded")
    public void test1() {
        driver.findElement(By.linkText("File Upload")).click();

        // provide path to the file
        // insert your path of the file into sendKeys() method's argument
        driver.findElement(By.id("file-upload")).sendKeys("/Users/Parvin/Desktop/class_notes.txt");

        // click submit
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(4);

        String expectedMessage = "File Uploaded!";
        String actualMessage = driver.findElement(By.xpath("//*[text() ='File Uploaded!']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "File was not uploaded");
        System.out.println(actualMessage);

        // make sure that it's correct file name
        String expectedFile = "class_notes.txt";
        String actualFile = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(actualFile, expectedFile, "File don't match");
        System.out.println("File name: " + actualFile);


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
