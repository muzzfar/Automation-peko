package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ForgotPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public ExtentTest test;

    private By forgotPasswordLink=By.xpath("//span[normalize-space()='Forgot Password?']");
    private By emailTextBox=By.xpath("//input[@placeholder='Enter your email id ']");
    private By sendResetLink=By.xpath("//span[normalize-space()='Send Reset Link']");
    private By verifyTestAfterClickingSendReset=By.xpath("//h3[normalize-space()='Check your email for reset link']");
    private By yopmailTextField=By.xpath("//input[@placeholder='Enter your inbox here']");
    private By yopmailArrowIcon=By.xpath("//div[@id='refreshbut']");
    private By mainIframe=By.xpath("//*[@name='ifinbox' and @id='ifinbox']");
    private By mainEmailContainer=By.xpath("//div[@class='mctn']");
    private By contentIframe=By.xpath("//*[@id='ifmail' and @name='ifmail' and @onload='onloadifmail()']");
    private By clickResetLink=By.xpath("(//table)[7]//tr//a");

    private By newCorporatePasswordField=By.xpath("//input[@name='password']");
    private By newCorporateConfirmPasswordField=By.xpath("//input[@name='confirmpassword']");
    private By passwordScreenNextButton=By.xpath("//button[@type='submit']");
    private By passwordUpdatedMessage=By.xpath("//h3[normalize-space()='Password Updated']");



    public ForgotPasswordPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void forgotPasswordDetails(String email,String newPass,String confirmPass){
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(emailTextBox)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(sendResetLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyTestAfterClickingSendReset));


        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);


        newTab.get("https://yopmail.com/");

        wait.until(ExpectedConditions.elementToBeClickable(yopmailTextField)).sendKeys(email);


        wait.until(ExpectedConditions.elementToBeClickable(yopmailArrowIcon)).click();

        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));

        //something different start
        WebElement emailElement = null;
        int attempts = 0;
        while (emailElement == null && attempts < 3) { // Retry up to 3 times
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mainIframe));
                // emailElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                //mainEmailContainer));
                emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        mainEmailContainer));
                List<WebElement> emails = emailElement.findElements(By.xpath("//div[@class='m']"));
                // Print the total number of emails
                System.out.println("Total number of emails: " + emails.size());

                // Click on the first email if available
                if (emails.size() > 0)
                {
                    emails.get(0).click();
                    System.out.println("First email clicked.");
                    // driver.switchTo().defaultContent();
                    driver.switchTo().parentFrame();
                    emialContent();
                    wait.until(ExpectedConditions.elementToBeClickable(newCorporatePasswordField)).sendKeys(newPass);
                    wait.until(ExpectedConditions.elementToBeClickable(newCorporateConfirmPasswordField)).sendKeys(confirmPass);
                    wait.until(ExpectedConditions.elementToBeClickable(passwordScreenNextButton)).click();
                 String text=   wait.until(ExpectedConditions.visibilityOfElementLocated(passwordUpdatedMessage)).getText();
                    System.out.println(text);

                }
                else
                {
                    System.out.println("No emails found.");
                    // waitForEmailWithTitle(driver, "peko");

                }

            } catch (Exception e) {

                e.printStackTrace();
            }
            if (emailElement == null) {
                // Refresh the page and retry
                driver.navigate().refresh();
                attempts++;
            }
        }
        //something different end
    }

    private void emialContent() {
        try {
            String originalWindow = driver.getWindowHandle();

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIframe));
            wait.until(ExpectedConditions.elementToBeClickable(clickResetLink)).click();

            // Wait for the new tab to open
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(3));

            Set<String> allWindowHandles = driver.getWindowHandles();
            // Convert the Set to a List to access the handles by index
            List<String> windowHandlesList = new ArrayList<>(allWindowHandles);


            driver.switchTo().window(windowHandlesList.get(2));
//
//            // Switch to the new tab
//            for (String handle : allWindowHandles) {
//                if (!handle.equals(originalWindow)) {
//                    driver.switchTo().window(handle);
//                    break;
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
