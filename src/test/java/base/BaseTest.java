package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;


import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

public class BaseTest {
    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    @BeforeSuite
    public void setup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Setup Test");


        try {
            //driver = new org.openqa.selenium.chrome.ChromeDriver();
            ChromeOptions options=new ChromeOptions();

            options.addArguments("--disable-notifications");
            //this is to open browser in incognito
            options.addArguments("--incognito");
            options.addArguments("--ash-no-nudges");

            driver = new ChromeDriver(options);

            test.info("Using Selenium 4 driver");

            //new implementation start for otp
            //here downcasting is done
            ChromeDriver abc=(ChromeDriver) driver;
            DevTools devTools=abc.getDevTools();
            //below is another way
           // DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();

            // Enable Network domain
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
            test.info("ChromeDriver initialized with CDP enabled.");
            //end

        } catch (Exception e) {
            test.info("Selenium 4 driver not available. Using WebDriverManager.");

            // Set up WebDriverManager to handle driver management
            try {
               // WebDriverManager.chromedriver().setup();
              //  WebDriverManager.firefoxdriver().setup();
            } catch (Exception ex) {
                test.info("Failed to setup WebDriverManager. Using fallback mechanism.");

                // Fallback to manual driver setup
                // String chromeDriverPath = "chromedriver"; // Update with your ChromeDriver path
                // String firefoxDriverPath = "geckodriver"; // Update with your GeckoDriver path

                // if (new File(chromeDriverPath).exists() && new File(firefoxDriverPath).exists()) {
                //     System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                //     System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                // } else {
                //     test.fail("Driver executables not found at specified paths. Please ensure drivers are available.");
                // Add additional handling or throw an exception if necessary
                // }
            }


            try {
                driver = new org.openqa.selenium.chrome.ChromeDriver();
                test.info("Using ChromeDriver");
            } catch (Exception ex) {
                test.fail("Failed to initialize ChromeDriver. Please check driver setup.");
                // Add additional handling or throw an exception if necessary
            }
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
        test.info("Driver initialized and navigated to URL: " + ConfigReader.getProperty("url"));

        //delete persistroot key
        // Execute JavaScript to delete specific local storage item
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.removeItem('persist:root');");

        // Refresh the page to ensure changes take effect (optional)
        driver.navigate().refresh();
        //end
    }
//old implemenation using only chromedriver
//    public void setup() {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        test = extent.createTest("Setup Test");
//
//
//        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//        ChromeOptions options=new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get(ConfigReader.getProperty("url"));
//        test.info("ChromeDriver initialized and navigated to URL: " + ConfigReader.getProperty("url"));
//
//    }
//End old implemenation using only chromedriver

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
            test.info("Driver quit successfully.");
        }
        if (extent != null) {
            extent.flush();
            test.info("Extent report flushed.");
        }
    }
}
