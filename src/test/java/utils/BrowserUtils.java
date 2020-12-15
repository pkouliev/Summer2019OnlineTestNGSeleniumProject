package utils;

public class BrowserUtils {

    // This method will be used to pause our test execution
    // provide number of seconds as a parameters
    public static void wait(int seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
