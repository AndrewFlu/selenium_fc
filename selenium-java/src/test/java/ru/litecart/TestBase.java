package ru.litecart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    protected void login(String username, String password) {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();
    }

    protected boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
