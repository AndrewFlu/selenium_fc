package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoodsPageOpensProperlyTest extends TestBase{

    @Test
    public void checkOpeningGoodsPage(){
        driver.get("http://localhost/litecart/en/");
        final WebElement campaignsBlock = driver.findElement(By.cssSelector("div#box-campaigns"));
        final List<WebElement> goods = campaignsBlock.findElements(By.cssSelector("li.product"));

        for (WebElement product : goods){

            Map<String, String> mainPageProductAttributes = new HashMap<>();

            final String mainPageName = product.findElement(By.cssSelector("div.name")).getAttribute("textContent");
            final String mainPagePrice = product.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
            final String mainPageSalePrice = product.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");
            final String mainPagePriceColor = product.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
            final String mainPagePriceDecoration = product.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line"); // line-through
            final Rectangle mainPagePriceDimensions = product.findElement(By.cssSelector("s.regular-price")).getRect();
            final String mainPageSalePriceColor = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
            final Rectangle mainPageSalePriceDimensions = product.findElement(By.cssSelector("strong.campaign-price")).getRect();
            final String mainPageSalePriceFont = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");

            mainPageProductAttributes.put("Name", mainPageName);
            mainPageProductAttributes.put("Price", mainPagePrice);
            mainPageProductAttributes.put("PriceColor", mainPagePriceColor);
            mainPageProductAttributes.put("PriceDecoration", mainPagePriceDecoration);
            mainPageProductAttributes.put("PriceDimensionsHeight", String.valueOf(mainPagePriceDimensions.height));
            mainPageProductAttributes.put("PriceDimensionsWidth", String.valueOf(mainPagePriceDimensions.width));
            mainPageProductAttributes.put("SalePrice", mainPageSalePrice);
            mainPageProductAttributes.put("SalePriceColor", mainPageSalePriceColor);
            mainPageProductAttributes.put("SalePriceDimensionsHeight", String.valueOf(mainPageSalePriceDimensions.height));
            mainPageProductAttributes.put("SalePriceDimensionsWidth", String.valueOf(mainPageSalePriceDimensions.width));
            mainPageProductAttributes.put("SalePriceFont", mainPageSalePriceFont);

            product.click();

            Map <String, String> childPageAttribute = new HashMap<>();
            final String childPageProductName = driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent");

            final WebElement childPagePriceElement = driver.findElement(By.cssSelector("div.information s.regular-price"));
            final String childPagePrice = childPagePriceElement.getAttribute("textContent");
            final String childPagePriceDecoration = childPagePriceElement.getCssValue("text-decoration-line");
            final Rectangle childPagePriceDimensions = childPagePriceElement.getRect();
            final String childPagePriceColor = childPagePriceElement.getCssValue("color");
            final WebElement childPageSalePriceElement = driver.findElement(By.cssSelector("div.information strong.campaign-price"));
            final String childPageSalePrice = childPageSalePriceElement.getAttribute("textContent");
            final String childPageSalePriceColor = childPageSalePriceElement.getCssValue("color");
            final String childPageSalePriceFont = childPageSalePriceElement.getCssValue("font-weight");
            final Rectangle childPageSalePriceDimensions = childPageSalePriceElement.getRect();

            childPageAttribute.put("Name", childPageProductName);
            childPageAttribute.put("Price", childPagePrice);
            childPageAttribute.put("PriceColor", childPagePriceColor);
            childPageAttribute.put("PriceDecoration", childPagePriceDecoration);
            childPageAttribute.put("PriceDimensionsHeight", String.valueOf(childPagePriceDimensions.height));
            childPageAttribute.put("PriceDimensionsWidth", String.valueOf(childPagePriceDimensions.width));
            childPageAttribute.put("SalePrice", childPageSalePrice);
            childPageAttribute.put("SalePriceColor", childPageSalePriceColor);
            childPageAttribute.put("SalePriceDimensionsHeight", String.valueOf(childPageSalePriceDimensions.height));
            childPageAttribute.put("SalePriceDimensionsWidth", String.valueOf(childPageSalePriceDimensions.width));
            childPageAttribute.put("SalePriceFont", childPageSalePriceFont);

            //10 a
            assertEquals(mainPageProductAttributes.get("Name"), childPageAttribute.get("Name"));

            //10 б
            assertEquals(mainPageProductAttributes.get("Price"), childPageAttribute.get("Price"));
            assertEquals(mainPageProductAttributes.get("SalePrice"), childPageAttribute.get("SalePrice"));

            //10 в
            assertEquals("line-through", mainPageProductAttributes.get("PriceDecoration"));
            assertTrue(mainPageProductAttributes.get("PriceColor").contains("119, 119, 119"));
            assertTrue(childPageAttribute.get("PriceColor").contains("102, 102, 102"));
            assertEquals("line-through", childPageAttribute.get("PriceDecoration"));

            //10 г
            assertTrue(mainPageProductAttributes.get("SalePriceColor").contains("204, 0, 0"));
            assertTrue(Integer.parseInt(mainPageProductAttributes.get("SalePriceFont")) >= 700);
            assertTrue(childPageAttribute.get("SalePriceColor").contains("204, 0, 0"));
            assertTrue(Integer.parseInt(childPageAttribute.get("SalePriceFont")) >= 700);

            //10д
            assertTrue(Integer.parseInt(mainPageProductAttributes.get("SalePriceDimensionsHeight")) > Integer.parseInt(mainPageProductAttributes.get("PriceDimensionsHeight")));
            assertTrue(Integer.parseInt(mainPageProductAttributes.get("SalePriceDimensionsWidth")) > Integer.parseInt(mainPageProductAttributes.get("PriceDimensionsWidth")));

            assertTrue(Integer.parseInt(childPageAttribute.get("SalePriceDimensionsHeight")) > Integer.parseInt(childPageAttribute.get("PriceDimensionsHeight")));
            assertTrue(Integer.parseInt(childPageAttribute.get("SalePriceDimensionsWidth")) > Integer.parseInt(childPageAttribute.get("PriceDimensionsWidth")));
        }

    }
}
