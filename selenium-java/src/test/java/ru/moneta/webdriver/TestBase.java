//package ru.moneta.webdriver;
//
//import org.junit.After;
//import org.junit.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class TestBase {
//
//    private static ThreadLocal <WebDriver> tlDriver = new ThreadLocal<>();
//    public WebDriver driver;
//    public WebDriverWait wait;
//
//    @Before
//    public void start() {
//        if (tlDriver.get() != null) {
//            driver = tlDriver.get();
//            wait = new WebDriverWait(driver, 5);
//            return;
//        }
//
//        driver = new FirefoxDriver();
//        tlDriver.set(driver);
//        wait = new WebDriverWait(driver, 5);
//
//        Runtime.getRuntime().addShutdownHook(
//                new Thread(() -> { driver.quit(); driver = null; }));
//    }
//
//    @After
//    public void stop() {
////        driver.quit();
////        driver = null;
//    }
//}

package ru.moneta.webdriver;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "WIN10");
        caps.setCapability("version", "11");
        caps.setCapability("browserName", "internet explorer");
        caps.setCapability("name", "Testing Selenium");

        driver = new RemoteWebDriver(
                new URL("http://9625024dc0784155f0136f9e5eda5c37:0e0e5abfcf2780c1da26959091e870bb@hub.testingbot.com/wd/hub"),
                caps);

//        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), new ChromeOptions());
        wait = new WebDriverWait(driver, 5);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
