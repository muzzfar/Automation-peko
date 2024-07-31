package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public ExtentTest test;

    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By homepageTextCheck=By.xpath("//span[text()='Dashboard']");
    private By logOutButton=By.xpath("//div[@class='ant-image css-1wwujzd']//img[@class='ant-image-img pl-4 cursor-pointer  css-1wwujzd']\n");


    public LoginPage(WebDriver driver,ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        test.info("Entered username: " + username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        test.info("Clicked on login button.");
    }
    public void checkHomePageText(String text){
        WebElement getText = wait.until(ExpectedConditions.elementToBeClickable(homepageTextCheck));
        test.info("HomePage text check performed. Visible text: " + getText.getText());
        System.out.println("login Successfully: HomePage is Visible: "+ getText);
    }
    public void logOutButtonMethod(){
        WebElement dk=wait.until(ExpectedConditions.presenceOfElementLocated(logOutButton));
        wait.until(ExpectedConditions.elementToBeClickable(dk)).click();
        test.info("logout successfully");
        System.out.println("logout success");
    String signInText=    driver.findElement(By.xpath("(//span[contains(@class,'css-1wwujzd')][normalize-space()='Sign in to your Peko account'])[1]")).getText();
        System.out.println(signInText);
    }
}
