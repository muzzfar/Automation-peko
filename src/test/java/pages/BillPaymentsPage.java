//package pages;
//
//import com.aventstack.extentreports.ExtentTest;
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class BillPaymentsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private Actions actions;
//    private JavascriptExecutor js;
//    public ExtentTest test;
//
//    private By billPaymentsLink = By.xpath("(//span[@class='ant-typography text-brandColor css-1wwujzd'])[1]");
//    private By etisalatPostpaidLink = By.xpath("//span[normalize-space()='Etisalat Postpaid']");
//    private By etisalatDropdown = By.xpath("//span[@class='ant-select-selection-placeholder']");
//    private By etisalatDropdownPostPaidSelection = By.xpath("(//div[contains(text(),'Postpaid')])[1]");
//    private By viewBillButton = By.xpath("//span[normalize-space()='View Bill']");
//    private By accountNumberField = By.xpath("//div//input[@name='accountNumber' and @placeholder='Example: 05XXXXX']");
//    private By editIcon = By.xpath("//span[@role='img' and @aria-label='edit']");
//    private By amountInputField = By.xpath("//div//input[@role='spinbutton' and @placeholder='Please Enter the amount']");
//    private By paymentMethod = By.xpath("//div//span[contains(text(),'Debit/Credit/ATM Cards')]");
//    private By submitButton = By.xpath("//button[@type='button']//span");
//    private By passwordFieldCvv = By.xpath("//div//input[@type='password']");
//    private By finalSubmitButton = By.xpath("(//div//button)[2]//span");
//    private By authenticationSuccessButton = By.xpath("//form//input[@type='submit' and @value='Authentication Successful']");
//    private By iframe = By.id("3ds_iframe");
//
//    //Du Prepaid
//    private By duPrepaidLink=By.xpath("//div//span[contains(text(),'Du Prepaid')]");
//    private By amountInputFieldDu=By.xpath("//div//input[@name='rechargeAmount' and @placeholder='Example: 100']");
//    private By nextButton=By.xpath("//button[@type='submit']");
//
//    public BillPaymentsPage(WebDriver driver, ExtentTest test) {
//        this.driver = driver;
//        this.test = test;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        this.actions = new Actions(driver);
//        this.js = (JavascriptExecutor) driver;
//    }
//
//    public void etisalatPostpaidDetails() {
//        try {
//            clickElement(billPaymentsLink);
//            clickElement(etisalatPostpaidLink);
//            ensurePageLoadComplete();
//
//            //wait.until(ExpectedConditions.elementToBeClickable(By.id("rc_select_3")));
//            WebElement dropdown = driver.findElement(By.id("rc_select_1"));
//            actions.moveToElement(dropdown).click().build().perform();
//            actions.sendKeys(Keys.ENTER).perform();
//
//            enterAccountNumber("0504470301");
//            clickElement(viewBillButton);
//            performCommonActions("20", "123");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void duPrepaid(){
//        clickElement(billPaymentsLink);
//        clickElement(duPrepaidLink);
//        enterAccountNumber("0551234561");
//        enterAmount("10");
//        clickElement(nextButton);
//        performCommonActions("20", "123");
//
//    }
//
//    private void performCommonActions(String amount, String password) {
//        clickElement(editIcon);
//
//        WebElement amountField = wait.until(ExpectedConditions.elementToBeClickable(amountInputField));
//        actions.moveToElement(amountField).click().perform();
//        js.executeScript("arguments[0].value = '';", amountField);
//        amountField.sendKeys(amount);
//
//        waitForPageLoad();
//        clickElement(paymentMethod);
//        clickElement(submitButton);
//        waitForPageLoad();
//
//        WebElement passwordFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldCvv));
//        passwordFieldElement.sendKeys(password);
//
//        ensurePageLoadComplete();
//        clickElement(finalSubmitButton);
//
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
//        WebElement successButton = wait.until(ExpectedConditions.visibilityOfElementLocated(authenticationSuccessButton));
//        successButton.click();
//        driver.switchTo().parentFrame();
//    }
//
//    private void clickElement(By locator) {
//        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
//    }
//
//    private void enterAccountNumber(String accountNumber) {
//        WebElement accountNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumberField));
//        accountNumberInput.sendKeys(accountNumber);
//    }
//    private void enterAmount(String amount) {
//        WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInputFieldDu));
//        amountInput.sendKeys(amount);
//    }
//
//    private void waitForPageLoad() {
//        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
//    }
//
//    private void ensurePageLoadComplete() {
//        js.executeAsyncScript(
//                "var callback = arguments[arguments.length - 1];" +
//                        "if (document.readyState !== 'complete') {" +
//                        "  window.addEventListener('load', callback);" +
//                        "} else {" +
//                        "  callback();" +
//                        "}"
//        );
//    }
//}
//




















package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BillPaymentsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public ExtentTest test;
    public Actions actions;
    public JavascriptExecutor js;

    private By billPaymentsLink= By.xpath("(//span[@class='ant-typography text-brandColor css-1wwujzd'])[1]");
    private By etisalatPostpaidLink=By.xpath("//span[normalize-space()='Etisalat Postpaid']");
    private By etisalatDropdown=By.xpath("//span[@class='ant-select-selection-placeholder']");
  //  private By etisalatDropdown=By.xpath("(//form//div)[2]//span[@unselectable='on']");
    private By etisalatDropdownPostPaidSelection=By.xpath("(//div[contains(text(),'Postpaid')])[1]");
    private By viewBillButton=By.xpath("//span[normalize-space()='View Bill']");
    //WebElement view=driver.findElement(RelativeLocator.with(By.xpath("//span[@class='ant-select-selection-placeholder']")).above(viewBillButton));

     private By waitforpostpaidtext=By.xpath("(//span[contains(@class,'ant-typography text-lg font-medium css-1wwujzd')][normalize-space()='Etisalat Postpaid'])[1]");
     private By mobileNumber=By.xpath("//div//input[@name='accountNumber' and @placeholder='Example: 05XXXXX']");
    //Du Prepaid
    private By billPaymentsLinkDu=By.xpath("//li//span[@role='img']/following-sibling::span/span[text()='Bill Payments']");
    private By duPrepaidLink=By.xpath("//div//span[contains(text(),'Du Prepaid')]");
    private By amountInputFieldDu=By.xpath("//div//input[@name='rechargeAmount' and @placeholder='Example: 100']");
    private By nextButton=By.xpath("//button[@type='submit']");

    //Du Postpaid
    private By duPostpaidLink=By.xpath("//div[@role='button']/span[text()='Du Postpaid']");

    //Etisalat Prepaid
    private By etisalatPrepaidLink=By.xpath("//div[@role='button']/span[text()='Etisalat Prepaid']");




    public BillPaymentsPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions=new Actions(driver);
        this.js=(JavascriptExecutor) driver;
    }


    public void etisalatPostpaidDetails() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(billPaymentsLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(etisalatPostpaidLink)).click();
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));

        Thread.sleep(6000);
        //WebElement d=driver.findElement(By.id("rc_select_3"));
     WebElement d=   getDynamicId();
        actions.moveToElement(d).click().build().perform();
        actions.sendKeys(Keys.ENTER).perform();

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//wait.until(ExpectedConditions.textToBe(waitforpostpaidtext,"Etisalat Postpaid"));
try {
   // wait.until(ExpectedConditions.textToBePresentInElementLocated(etisalatDropdown, "Please select a service type"));
   // wait.until(ExpectedConditions.visibilityOfElementLocated(etisalatDropdown));
  //  wait.until(ExpectedConditions.elementToBeClickable(etisalatDropdown)).click();
    //wait.until(ExpectedConditions.presenceOfElementLocated(RelativeLocator.with(etisalatDropdown).below(etisalatDropdownPostPaidSelection)));
   // WebElement view=driver.findElement(RelativeLocator.with(By.xpath("//span[@class='ant-select-selection-placeholder']")).above(viewBillButton));
//    JavascriptExecutor js=(JavascriptExecutor) driver;
//    String[] selectors={"#rc_select_3","div.ant-select-item-option-content"};
//   // WebElement element = (WebElement) js.executeScript("return document.querySelector(\"#rc_select_3\");");
//    WebElement element = (WebElement) js.executeScript("return document.querySelector(arguments[0]);", selectors);
//    js.executeScript("arguments[0].click();", element);


    //js starts
    // Initialize JavaScript Executor
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//
//    // Define your selectors
//    String[] selectors = {"#rc_select_3", "div.ant-select-item-option-content"};
//
//    // Select the first element using querySelector
//    WebElement firstElement = (WebElement) js.executeScript("return document.querySelector(arguments[0]);", selectors[0]);
//
//    if (firstElement != null) {
//        js.executeScript("arguments[0].click();", firstElement);
//    } else {
//        System.out.println("First element not found.");
//    }
//
//    // Select all elements using querySelectorAll for the second selector
//    List<WebElement> elements = (List<WebElement>) js.executeScript(
//            "return Array.from(document.querySelectorAll(arguments[0]));", selectors[1]);
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//    // Loop through the elements and perform actions as needed
//    for (WebElement element : elements) {
//        if (element.getText().trim().equals("Postpaid")) { // Example condition
//            js.executeScript("arguments[0].click();", element);
//            break; // Exit loop after clicking
//        }
//    }
    //js ends

    driver.findElement(mobileNumber).sendKeys("0504470301");
    Thread.sleep(4000);
    driver.findElement(By.xpath("//button[@type='submit']/span[contains(text(),'View Bill')]")).click();
    Thread.sleep(5000);
    commonPaymentsPage();
    commonCardsPayment();
     }catch (Exception e){
        e.printStackTrace();
        e.getMessage();
        e.fillInStackTrace();

    }
}
public void duPrepaidDetails() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(billPaymentsLinkDu)).click();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(duPrepaidLink)).click();
    driver.findElement(By.xpath("//div//input[@name='accountNumber' and @placeholder='Example: 05XXXXX']")).sendKeys("0551234561");

    wait.until(ExpectedConditions.elementToBeClickable(amountInputFieldDu)).sendKeys("10");
    wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    commonPaymentsPage();
    commonCardsPayment();



}
public void duPostpaidDetails(){
        wait.until(ExpectedConditions.elementToBeClickable(billPaymentsLinkDu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(duPostpaidLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(mobileNumber)).sendKeys("0551234567");
        wait.until(ExpectedConditions.elementToBeClickable(viewBillButton)).click();
        commonPaymentsPage();
        commonCardsPayment();
}
public void etisalatPrepaidDetails(){
    wait.until(ExpectedConditions.elementToBeClickable(billPaymentsLinkDu)).click();
    wait.until(ExpectedConditions.elementToBeClickable(etisalatPrepaidLink)).click();
    wait.until(ExpectedConditions.elementToBeClickable(mobileNumber)).sendKeys("0504930554");
    wait.until(ExpectedConditions.elementToBeClickable(amountInputFieldDu)).sendKeys("10");
    wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    commonPaymentsPage();
    commonCardsPayment();



}
public void commonPaymentsPage(){
        try {
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[@role='img' and @aria-label='edit']")).click();
            Thread.sleep(4000);
            //for this element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement dk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//input[@role='spinbutton' and @placeholder='Please Enter the amount']")));
            actions.moveToElement(dk).click().perform();

            js.executeScript("arguments[0].value = '';", dk);


            // Scroll into view if necessary
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dk);


            dk.sendKeys("20");

            //end

            Thread.sleep(5000);
            driver.findElement(By.xpath("//div//span[contains(text(),'Debit/Credit/ATM Cards')]")).click();
            driver.findElement(By.xpath("//button[@type='button']//span")).click();
        }catch (InterruptedException interruptedException){
            System.out.println(interruptedException.getMessage());
        }
}
public void commonCardsPayment(){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
            Thread.sleep(10000);
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            ensurePageLoad();
            WebElement kd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//input[@type='password']")));
            kd.sendKeys("123");
            Thread.sleep(3000);

            ensurePageLoad();


            //driver.findElement(By.xpath("//div//input[@type='password']")).sendKeys("123");
            driver.findElement(By.xpath("(//div//button)[2]//span")).click();
            Thread.sleep(8000);
            driver.switchTo().frame("3ds_iframe");
            WebElement sk = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form//input[@type='submit' and @value='Authentication Successful']")));
            sk.click();
            driver.switchTo().parentFrame();
            Thread.sleep(10000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//button[@type='button']//span[text()='Go to Dashboard']"))).click();
        }catch (InterruptedException interruptedException){
            interruptedException.getMessage();
            interruptedException.fillInStackTrace();
            interruptedException.printStackTrace();
            //interruptedException.printStackTrace(System.out);
        }
}
public void ensurePageLoad(){
    js.executeAsyncScript(
            "var callback = arguments[arguments.length - 1];" +
                    "if (document.readyState !== 'complete') {" +
                    "  window.addEventListener('load', callback);" +
                    "} else {" +
                    "  callback();" +
                    "}"
    );
}
public WebElement getDynamicId(){

     WebElement c=   driver.findElement(By.xpath("//div/span/input[@type='search' and @role='combobox']"));
   String dynamicpart=  c.getAttribute("id").split("_")[2];
   String dynamicXpath="//*[@id='rc_select_"+ dynamicpart +"']";
  return driver.findElement(By.xpath(dynamicXpath));

}
}
