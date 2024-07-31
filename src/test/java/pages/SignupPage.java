package pages;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;
import utils.HelperClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.openqa.selenium.devtools.events.CdpEventTypes.consoleEvent;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public ExtentTest test;

    private By signupLink = By.xpath("//a//span[text()='Sign up']");
    private By fullNameField = By.xpath("//input[@name='contactPersonName']");
    private By companyNameField = By.xpath("//input[@placeholder='Company Name']");
    private By mobileNumberField = By.xpath("//input[@placeholder='Mobile Number']");
    private By businessEmail=By.xpath("//input[@placeholder='Business Email']");
    private By nextButton=By.xpath("//button[@type='submit']");

    private By newCorporatePasswordField=By.xpath("//input[@name='password']");
    private By newCorporateConfirmPasswordField=By.xpath("//input[@name='confirmpassword']");
    private By passwordScreenNextButton=By.xpath("//button[@type='submit']");

    private By yopmailTextField=By.xpath("//input[@placeholder='Enter your inbox here']");
    private By yopmailArrowIcon=By.xpath("//div[@id='refreshbut']");
    private By mainIframe=By.xpath("//*[@name='ifinbox' and @id='ifinbox']");
    private By mainEmailContainer=By.xpath("//div[@class='mctn']");
   // private By emailListContainer=By.xpath("//div[@class='m']");

   // private By contentIframe=By.xpath("//*[@id='ifmail' and @name='ifmail']");
    private By contentIframe=By.xpath("//*[@id='ifmail' and @name='ifmail' and @onload='onloadifmail()']");
    private By getOtp=By.xpath("(//table)[7]//tr[2]//span");

    private By verifyotpText=By.xpath("//h5[normalize-space()='Verify your email']");
    private By otpBox=By.xpath("//div//input[@aria-label='Please enter OTP character 1']");
    private By verifyOtpAndSubmit=By.xpath("//span[normalize-space()='Verify and Submit']");






    public SignupPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void addSignupDetails(String personName,String compName,String mNumber,String bEmail
    ,String newPass, String confirmPass
    ,String yopmailEmail){

        wait.until(ExpectedConditions.elementToBeClickable(signupLink)).click();
        test.info("clicked on signup link");

        wait.until(ExpectedConditions.elementToBeClickable(fullNameField)).sendKeys(personName);
        test.info("Entered name in full Name field "+personName);

        wait.until(ExpectedConditions.elementToBeClickable(companyNameField)).sendKeys(compName);
        test.info("Entered company name in company name field "+compName);

        wait.until(ExpectedConditions.elementToBeClickable(mobileNumberField)).sendKeys(mNumber);
        test.info("Entered mobile number in Mobile Number field "+mNumber);

        wait.until(ExpectedConditions.elementToBeClickable(businessEmail)).sendKeys(bEmail);
        test.info("Entered email in business email field "+bEmail);

        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        test.info("clicked on next button to submit all signup details");

        wait.until(ExpectedConditions.elementToBeClickable(newCorporatePasswordField)).sendKeys(newPass);
        test.info("Entered New Password");

        wait.until(ExpectedConditions.elementToBeClickable(newCorporateConfirmPasswordField)).sendKeys(confirmPass);
        test.info("Entered password to confirm");

        wait.until(ExpectedConditions.elementToBeClickable(passwordScreenNextButton)).click();
        test.info("Clicked Next or Submit on Password screen");
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
//                "//h5[normalize-space()='Verify your email']"
//        ))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyotpText));
        test.info("Verify your email page loaded successfully");


        // Open a new tab
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);

        // Navigate to a different URL in the new tab
        newTab.get("https://yopmail.com/");

        wait.until(ExpectedConditions.elementToBeClickable(yopmailTextField)).sendKeys(yopmailEmail);
        test.info("Entered email that is used for corporate account creation");

        wait.until(ExpectedConditions.elementToBeClickable(yopmailArrowIcon)).click();
        test.info("clicked arrow or enter key press to get email content otp ");
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mainIframe));
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
                    //emialContent();
                    // Wait for email content and OTP retrieval
                    String otpFromEmail = emialContent();
                    //continueSignupProcess();
                    continueSignupProcess(otpFromEmail);
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

        // Locate the email list container
      //  WebElement emailListContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(mainEmailContainer));
        // Get all the email elements inside the container
        // List<WebElement> emails=  wait.until(ExpectedConditions.visibilityOfAllElements(emailListContainer));
       // List<WebElement> emails = emailListContainer.findElements(By.xpath("//div[@class='m']"));
//        List<WebElement> emails = emailElement.findElements(By.xpath("//div[@class='m']"));

//        // Print the total number of emails
//        System.out.println("Total number of emails: " + emails.size());
//
//        // Click on the first email if available
//        if (emails.size() > 0)
//        {
//            emails.get(0).click();
//            System.out.println("First email clicked.");
//            emialContent();
//            continueSignupProcess();
//        }
//        else
//        {
//            System.out.println("No emails found.");
//
//
//        }
    }



    private void continueSignupProcess(String otpFromEmail) {
        try {
            // Switch back to the original tab
            Set<String> handles = driver.getWindowHandles();
            List<String> handlesList = new ArrayList<>(handles);
            driver.switchTo().window(handlesList.get(0));

            // Wait for OTP input box to be clickable and enter OTP
            Actions actions = new Actions(driver);
            WebElement otpInputBox = wait.until(ExpectedConditions.elementToBeClickable(otpBox));
            actions.moveToElement(otpInputBox).click().sendKeys(otpFromEmail).perform();

            // click on verify and submit button
            wait.until(ExpectedConditions.elementToBeClickable(verifyOtpAndSubmit)).click();

            // Extract the mobile OTP from console logs
            // Extract mobile OTP using CDP
            String mobileOtp = extractMobileOtpFromCDP();
            if (mobileOtp != null) {
                // Enter mobile OTP
                WebElement mobileOtpInput = wait.until(ExpectedConditions.elementToBeClickable(otpBox));
                mobileOtpInput.sendKeys(mobileOtp);
                test.info("Entered Mobile OTP: " + mobileOtp);

                // Click on Verify and Submit button
                wait.until(ExpectedConditions.elementToBeClickable(verifyOtpAndSubmit)).click();
                test.info("Clicked on Verify and Submit button for Mobile OTP");
            } else {
                test.fail("Failed to extract Mobile OTP using CDP.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    private String emialContent() {
        try {
            // Switch to the iframe where the email content is located
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIframe));

            // Retrieve OTP from the email content
            String otp = wait.until(ExpectedConditions.visibilityOfElementLocated(getOtp)).getText();
            System.out.println("OTP is: " + otp);

            // Switch back to the default content (main application)
            driver.switchTo().defaultContent();

            return otp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String extractMobileOtpFromCDP() {
        try {
            // Initialize DevTools session
            DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();

            // Enable Network domain
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            // Listen for network requests and capture mobile OTP
            devTools.addListener(Network.responseReceived(), responseReceived -> {
//                try {
//                    String url = responseReceived.getResponse().getUrl();
//                    if (url.contains("https://api-gateway.orangeocean-fa607906.uaenorth.azurecontainerapps.io/api/v1/user/verify-emailOtp")) {
//                        devTools.send(Network.getResponseBody(responseReceived.getRequestId()), responseBody ->
//                        {
//                            String response = responseBody.get("body").toString();
//
//                            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
//                            String mobileOtp = json.getAsJsonObject("data").get("phoneOtp").getAsString();
//                            System.out.println("Extracted Mobile OTP using CDP: " + mobileOtp);
//                        });
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            });

            return null; // Return extracted mobile OTP here
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
