package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;


public class testBase {

    public WebDriver driver;
    public WebDriverWait wait;

    public String loadData(String property) throws IOException {
        Properties prop = readPropertiesFile("resources/data.properties");
        return prop.getProperty(property);
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }


    @BeforeClass
    public void initDriver() throws IOException {
        String browser = loadData("browser");
        System.out.println("browser : " + browser);
        if (Objects.equals(browser, "chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions CHOptions = new ChromeOptions();
            CHOptions.addArguments("--lang=en-GB"); // set chrome language to be english
            driver = new ChromeDriver(CHOptions);
        }
        else if (Objects.equals(browser, "firefox")){
            WebDriverManager.firefoxdriver().setup();

            FirefoxProfile profile = new FirefoxProfile();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "en-GB");
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
        }
        else if (Objects.equals(browser, "edge")){
            WebDriverManager.edgedriver().setup();
            EdgeOptions EOptions = new EdgeOptions();
            EOptions.addArguments("--lang=en-GB");
            driver = new EdgeDriver(EOptions);
        }
        driver.manage().window().maximize();

    }

    @AfterSuite
    public void stopDriver()
    {
        driver.quit();
    }
}
