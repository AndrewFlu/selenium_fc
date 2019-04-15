package ru.moneta.webdriver;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @Before
    public void start() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null;})
        );
    }

    @After
    public void stop() {
//        driver.quit();
//        driver = null;
    }
}
