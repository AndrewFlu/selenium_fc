package ru.moneta.webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirefoxTinkoffTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void init() {
        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver = new FirefoxDriver(options);
        Cookie cookieNamed = driver.manage().getCookieNamed("test");
        driver.manage().deleteCookie(cookieNamed);
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.manage().deleteAllCookies();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 5);
    }


    @Test
    public void testTinkoffMobilePage() {
        driver.get("https://www.tinkoff.ru/");
        driver.findElement(By.xpath("//div[@class='header__2TQTh']//span[5]//a[1]")).click();
        wait.until(titleIs("Тинькофф Мобайл — связь, какой она должна быть"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
