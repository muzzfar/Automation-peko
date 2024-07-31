package tests;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    //@Ignore
    @Test(priority = 0)
    public void loginTest() {
        ExtentTest test = extent.createTest("Login Test").info("Logging in with Lando account");

        LoginPage loginPage = new LoginPage(driver,test);
        loginPage.login("lando@yopmail.com", "Admin@1234");

        test.info("Logged in successfully");
        loginPage.checkHomePageText("Personal Information");
        test.info("Checked homepage text successfully");
       // loginPage.logOutButtonMethod();
        test.info("logout successfully");

    }
}
