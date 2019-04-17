package ru.moneta.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ThirdSearch extends TestBase {

    @Test
    public void firstDuckDuckGoSearchTest() {
        driver.navigate().to("https://duckduckgo.com/");
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("tinkoff");
        driver.findElement(By.id("search_button_homepage")).click();
        wait.until(titleIs("tinkoff at DuckDuckGo"));
    }

    @Test
    public void secondDuckDuckGoSearchTest() {
        driver.navigate().to("https://duckduckgo.com/");
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("tinkoff");
        driver.findElement(By.id("search_button_homepage")).click();
        wait.until(titleIs("tinkoff at DuckDuckGo"));
    }

    @Test
    public void thirdDuckDuckGoSearchTest() {
        driver.navigate().to("https://duckduckgo.com/");
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("tinkoff");
        driver.findElement(By.id("search_button_homepage")).click();
        wait.until(titleIs("tinkoff at DuckDuckGo"));
    }

    @Test
    public void forthDuckDuckGoSearchTest() {
        driver.navigate().to("https://duckduckgo.com/");
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("tinkoff");
        driver.findElement(By.id("search_button_homepage")).click();
        wait.until(titleIs("tinkoff at DuckDuckGo"));
    }

}
