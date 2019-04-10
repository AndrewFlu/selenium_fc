package ru.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class AdminLoginTest {

    private WebDriver driver;

    @Before
    public void init() {
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginInAdministrativeArea() {
        String adminLogin = "admin";
        String adminPassword = "admin";
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys(adminLogin);
        driver.findElement(By.name("password")).sendKeys(adminPassword);
        driver.findElement(By.name("login")).click();
        assertTrue(driver.findElement(By.id("box-apps-menu-wrapper")).isDisplayed());
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
