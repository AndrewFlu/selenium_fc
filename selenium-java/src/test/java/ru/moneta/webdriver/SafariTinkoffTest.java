package ru.moneta.webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SafariTinkoffTest {


    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void init() {
        driver = new SafariDriver();
        System.out.println(((SafariDriver) driver).getCapabilities());
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
