package ru.different.browsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ChromeTinkoffTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void init() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(ChromeOptions.CAPABILITY, options);

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//        driver = new InternetExplorerDriver(caps);

        driver = new RemoteWebDriver(new URL("http://192.168.0.102:4444/"), options);
//        System.out.println(((ChromeDriver) driver).getCapabilities());
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
