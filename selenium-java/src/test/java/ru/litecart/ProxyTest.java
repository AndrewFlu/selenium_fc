package ru.litecart;

import net.lightbody.bmp.core.har.Har;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProxyTest extends AdminLoginTest.TestBase {

    @Test
    public  void testProxy(){
        String username = "admin";
        String password = "admin";
        Map<Integer, String> statusCode = new HashMap<>();

        proxy.newHar();

        driver.get("http://localhost/litecart/admin/login.php");
        login(username, password);
        goToCatalog();
        openRootCategory();

        List<WebElement> listOfProducts = driver.findElements(By.xpath("//form/table/tbody//tr[td//img]"));

        for (int i = 1; i <= listOfProducts.size(); i++) {
            driver.findElement(By.xpath(String.format("//form/table/tbody/tr[td/img][%d]/td[3]/a", i))).click();
            driver.navigate().back();
        }

        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l-> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
        har.getLog().getEntries().forEach(l->statusCode.put(l.getResponse().getStatus(), l.getRequest().getUrl()));

    }


    private void openRootCategory() {
        driver.findElement(By.cssSelector("table.dataTable tr:nth-of-type(3) td:nth-of-type(3) a")).click();
    }

    private void goToCatalog() {
        driver.findElement(By.cssSelector("div#box-apps-menu-wrapper li:nth-of-type(2)")).click();
    }
}
