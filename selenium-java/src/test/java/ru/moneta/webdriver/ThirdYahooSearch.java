package ru.moneta.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ThirdYahooSearch extends TestBase {

    @Test
    public void firstYahooSearchTest() {
        driver.navigate().to("https://yahoo.com");
        driver.findElement(By.cssSelector("input[name='p']")).sendKeys("tinkoff");
        driver.findElement(By.id("uh-search-button")).click();
        wait.until(titleIs("tinkoff - Yahoo Search Results"));
    }

    @Test
    public void secondYahooSearchTest() {
        driver.navigate().to("https://yahoo.com");
        driver.findElement(By.cssSelector("input[name='p']")).sendKeys("tinkoff");
        driver.findElement(By.id("uh-search-button")).click();
        wait.until(titleIs("tinkoff - Yahoo Search Results"));
    }

    @Test
    public void thirdYahooSearchTest() {
        driver.navigate().to("https://yahoo.com");
        driver.findElement(By.cssSelector("input[name='p']")).sendKeys("tinkoff");
        driver.findElement(By.id("uh-search-button")).click();
        wait.until(titleIs("tinkoff - Yahoo Search Results"));
    }

    @Test
    public void forthYahooSearchTest() {
        driver.navigate().to("https://yahoo.com");
        driver.findElement(By.cssSelector("input[name='p']")).sendKeys("tinkoff");
        driver.findElement(By.id("uh-search-button")).click();
        wait.until(titleIs("tinkoff - Yahoo Search Results"));
    }

}
