package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.SignupPage;

import java.util.Random;

public class ForgotPasswordPageTest extends BaseTest {
    @Ignore
    @Test(priority = 2)
    public void forgotPasswordTest(){
        ExtentTest test = extent.createTest("Reset Password Test").info("Testing Reset Password functionality");
Random random=new Random();
        int randomNum = random.nextInt(10000);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver,test);
        String password="Admin@1234"+randomNum;
        forgotPasswordPage.forgotPasswordDetails("cisco@yopmail.com",password,password);


    }
}
