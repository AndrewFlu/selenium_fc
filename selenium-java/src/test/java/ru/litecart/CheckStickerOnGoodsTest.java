package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CheckStickerOnGoodsTest extends TestBase {

    @Test
    public void testGoodsHaveStickers(){
        driver.get("http://localhost/litecart/en/");
        List <WebElement> products = driver.findElements(By.cssSelector("ul.products li"));

        assertTrue(products.size() > 0);

        for (WebElement product : products){
            assertTrue(isElementPresent(By.cssSelector("div.sticker")));
            List<WebElement> stickersInProduct = product.findElements(By.cssSelector("div.sticker"));
            assertEquals("У товара должен быть только один стикер", 1, stickersInProduct.size());
        }
    }
}
