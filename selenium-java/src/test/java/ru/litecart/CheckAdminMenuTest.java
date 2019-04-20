package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CheckAdminMenuTest extends TestBase {

    @Test
    public void testAdminMenu() {
        String username = "admin";
        String password = "admin";
        String baseUrl = "http://localhost/litecart/admin/login.php";

        driver.get(baseUrl);
        login(username, password);

        List<WebElement> menuItems = driver.findElements(By.cssSelector("ul.list-vertical > li"));

        assertTrue(menuItems.size() > 0);

        for (int i = 1; i <= menuItems.size(); i++) {

            WebElement menuItem = driver.findElement(By.cssSelector(String.format("ul.list-vertical > li:nth-of-type(%d)", i)));
            menuItem.click();
            assertTrue(isElementPresent(By.tagName("h1")));

            List<WebElement> childItems = driver.findElements(By.cssSelector("ul.docs li"));
            for (int k = 1; k <= childItems.size(); k++) {

                WebElement childItem = driver.findElement(By.cssSelector(String.format("ul.docs li:nth-of-type(%d)", k)));
                childItem.click();
                assertTrue(isElementPresent(By.cssSelector("h1")));
            }
        }
    }
}
