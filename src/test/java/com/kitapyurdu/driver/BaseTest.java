package com.kitapyurdu.driver;

import com.kitapyurdu.config.GlobalConfig;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    public static WebDriver driver;
    GlobalConfig config = new GlobalConfig();

    @Before
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(BrowserOptions.DISABLENOTIFICATIONS.getOptions());
            chromeOptions.addArguments(BrowserOptions.DISABLEGPU.getOptions());
            chromeOptions.addArguments(BrowserOptions.NOSANDBOX.getOptions());
            chromeOptions.addArguments(BrowserOptions.DISABLEPLUGINS.getOptions());
            chromeOptions.addArguments(BrowserOptions.DISABLEPOPUPBLOCKING.getOptions());
            chromeOptions.addArguments(BrowserOptions.IGNORECERTIFICATEERRORS.getOptions());
            chromeOptions.addArguments(BrowserOptions.DISABLETRANSLATE.getOptions());

            driver = new ChromeDriver(chromeOptions); // apply options
            driver.manage().window().maximize();
            driver.get(config.getWebsiteUrl());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        } else {
            System.out.println("Error: driver is null");
        }
    }
}
