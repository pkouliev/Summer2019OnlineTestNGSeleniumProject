package tests.day3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class QuitVSClose {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/open_new_tab");

        Thread.sleep(10000); // will pause program execution for 4 seconds
        // sleep method throws checked exception, that needs to be taken care of
        // before running a program. Otherwise, there will be a compilation error.
        // Just add "throws InterruptedException" in the method signature

        driver.close(); // will close only one last opened tab

        Thread.sleep(10000);

        driver.quit(); // will shutdown entire opened browser

    }
}
