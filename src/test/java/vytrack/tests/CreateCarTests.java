package vytrack.tests;

import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.ConfigurationReader;
import utils.ExcelUtil;
import vytrack.pages.CreateCarPage;
import vytrack.pages.LoginPage;
import vytrack.pages.VehiclesPage;

import java.util.List;
import java.util.Map;

public class CreateCarTests extends TestBase {

    @Test(description = "Create some random car")
    public void test1() {
        extentTest = extentReports.createTest("Create a new car");

        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        CreateCarPage createCarPage = new CreateCarPage();

        loginPage.loginQA1();
        loginPage.navigateTo("Fleet", "Vehicles");

        vehiclesPage.clickToCreateCar();
        loginPage.waitUntilLoaderMaskDisappear();

        createCarPage.enterLicensePlate("Random");
        createCarPage.selectTags("Compact");
        createCarPage.selectFuelType("Diesel");

        createCarPage.saveAndCloseButtonElement.click();

        extentTest.pass("New car was created");

    }

    @Test(description = "Create a car by reading test data from excel file")
    public void createCarTest() {
        extentTest = extentReports.createTest("Create a car by reading test data from excel file");

        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        CreateCarPage createCarPage = new CreateCarPage();

        String username = ConfigurationReader.getValue("user_name");
        String password = ConfigurationReader.getValue("password");

        loginPage.login(username, password);

        loginPage.navigateTo("Fleet", "Vehicles");

        loginPage.waitUntilLoaderMaskDisappear();

        vehiclesPage.clickToCreateCar();

        loginPage.waitUntilLoaderMaskDisappear();

        ExcelUtil excelUtil = new ExcelUtil("cars.xlsx", "cars");

        // read data from excel spreadsheet as List of Map
        // testData it's just reference variable
        List<Map<String, String>> testData = excelUtil.getDataList();

        // 0 means data from first row, License Plate, it's a column name
        // so we are reading from first row and License Plate column
        createCarPage.licensePlateElement.sendKeys(testData.get(0).get("License Plate"));

        // enter driver info
        createCarPage.driverElement.sendKeys(testData.get(0).get("Driver"));
        // enter model year
        createCarPage.modelYearElement.sendKeys(testData.get(0).get("Model Year"));
        // enter color
        createCarPage.colorElement.sendKeys(testData.get(0).get("Color"));

        loginPage.waitUntilLoaderMaskDisappear();
        // click to save and close
        createCarPage.saveAndCloseButtonElement.click();

        BrowserUtils.wait(3); // for demo
        extentTest.info("Created a new car");

        // with many rows of data - just example
        for (Map<String, String> value : testData) {
            createCarPage.licensePlateElement.sendKeys(value.get("License Plate"));
        }
    }
}
