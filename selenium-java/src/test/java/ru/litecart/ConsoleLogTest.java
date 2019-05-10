package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConsoleLogTest extends TestBase {

    @Test
    public void consoleLogTest() {
        String username = "admin";
        String password = "admin";
        driver.get("http://localhost/litecart/admin/login.php");
        login(username, password);
        goToCatalog();
        openRootCategory();

        List<WebElement> listOfProducts = driver.findElements(By.xpath("//form/table/tbody//tr[td//img]"));

        for (int i = 1; i <= listOfProducts.size(); i++) {
            driver.findElement(By.xpath(String.format("//form/table/tbody/tr[td/img][%d]/td[3]/a", i))).click();
            driver.navigate().back();
        }

    }


    private void openRootCategory() {
        driver.findElement(By.cssSelector("table.dataTable tr:nth-of-type(3) td:nth-of-type(3) a")).click();
    }

    private void goToCatalog() {
        driver.findElement(By.cssSelector("div#box-apps-menu-wrapper li:nth-of-type(2)")).click();
    }
}
