package vytrack.tests;

import org.testng.annotations.Test;
import utils.TestBase;
import vytrack.pages.CreateCarPage;
import vytrack.pages.LoginPage;
import vytrack.pages.VehiclesPage;

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
}
