package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class AdminLoginTest extends TestBase {

    @Test
    public void testLoginInAdministrativeArea() {
        String adminLogin = "admin";
        String adminPassword = "admin";
        driver.get("http://localhost/litecart/admin/login.php");
        login(adminLogin, adminPassword);
        assertTrue(driver.findElement(By.id("box-apps-menu-wrapper")).isDisplayed());
    }
}
