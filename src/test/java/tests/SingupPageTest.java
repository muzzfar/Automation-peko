package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.SignupPage;
import utils.HelperClass;

public class SingupPageTest extends BaseTest {
    @Ignore
    @Test(priority=1)
    public void signupTest(){
        ExtentTest test = extent.createTest("Signup Test").info("Creating corporate Account");

        SignupPage signupPage = new SignupPage(driver,test);
        HelperClass helperClass=new HelperClass();
        // Generate a random email and store it in a variable
        String randomEmail = HelperClass.generateRandomEmail();

        signupPage.addSignupDetails(HelperClass.generateRandomFullName(),
                "Tesla"+System.currentTimeMillis()+"Private Limited",
                helperClass.generatePhoneNumber(),
               randomEmail,
                "Admin@1234",
                "Admin@1234",
                randomEmail);

    }
}
