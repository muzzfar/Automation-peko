package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.BillPaymentsPage;
import pages.LoginPage;

public class BillPaymentsPageTest extends BaseTest {
//(dependsOnMethods = {"loginTest"})
    ExtentTest test;

    //@Ignore
    @Test
    public void billPaymentsTest() throws InterruptedException {
         test = extent.createTest("Etisalat Postpaid").info("Filling Etisalat Postpaid Details");

        BillPaymentsPage billPaymentsPage = new BillPaymentsPage(driver,test);

        billPaymentsPage.etisalatPostpaidDetails();

    }
    @Ignore
    @Test
    public void duPrepaidTest() throws InterruptedException {
        test = extent.createTest("DU Prepaid").info("Filling DU Prepaid Details");
        BillPaymentsPage billPaymentsPage=new BillPaymentsPage(driver,test);

        billPaymentsPage.duPrepaidDetails();
    }
    @Ignore
    @Test
    public void duPostpaidTest() {
        test=extent.createTest("Du Postpaid").info("Filling Du postpaid Details");
        BillPaymentsPage billPaymentsPage=new BillPaymentsPage(driver,test);
        billPaymentsPage.duPostpaidDetails();
    }
    @Ignore
    @Test
    public void etisalatPrepaidTest(){
        test=extent.createTest("Etisalat Prepaid").info("Filling Etisalat prepaid Details");
        BillPaymentsPage billPaymentsPage=new BillPaymentsPage(driver,test);
        billPaymentsPage.etisalatPrepaidDetails();
    }
}
