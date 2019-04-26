package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoodsPageOpensProperlyTest extends TestBase {

    @Test
    public void checkOpeningGoodsPage() {
        String red;
        String green;
        String blue;

        driver.get("http://localhost/litecart/en/");
        final WebElement campaignsBlock = driver.findElement(By.cssSelector("div#box-campaigns"));
        final List<WebElement> goods = campaignsBlock.findElements(By.cssSelector("li.product"));

        for (WebElement product : goods) {

            final String mainPageName = product.findElement(By.cssSelector("div.name")).getAttribute("textContent");
            final String mainPagePrice = product.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
            final String mainPageSalePrice = product.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");
            final String mainPagePriceColor = product.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
            final String mainPagePriceDecoration = product.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line"); // line-through
            final Rectangle mainPagePriceDimensions = product.findElement(By.cssSelector("s.regular-price")).getRect();
            final String mainPageSalePriceColor = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
            final Rectangle mainPageSalePriceDimensions = product.findElement(By.cssSelector("strong.campaign-price")).getRect();
            final String mainPageSalePriceFont = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");

            product.click();

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

            //10 a
            assertEquals(mainPageName, childPageProductName);

            //10 б
            assertEquals(mainPagePrice, childPagePrice);
            assertEquals(mainPageSalePrice, childPageSalePrice);

            //10 в
            assertEquals("line-through", mainPagePriceDecoration);
            assertEquals("line-through", childPagePriceDecoration);

            red = getColorCode(mainPagePriceColor, 1);
            green = getColorCode(mainPagePriceColor, 2);
            blue = getColorCode(mainPagePriceColor, 3);

            assertEquals(red, green);
            assertEquals(red, blue);

            red = getColorCode(childPagePriceColor, 1);
            green = getColorCode(childPagePriceColor, 2);
            blue = getColorCode(childPagePriceColor, 3);

            assertEquals(red, green);
            assertEquals(red, blue);


            //10 г
            green = getColorCode(mainPageSalePriceColor, 2);
            blue = getColorCode(mainPageSalePriceColor, 3);

            assertTrue("0".equals(green));
            assertTrue("0".equals(blue));

            green = getColorCode(childPageSalePriceColor, 2);
            blue = getColorCode(childPageSalePriceColor, 3);

            assertTrue("0".equals(green));
            assertTrue("0".equals(blue));

            assertTrue(Integer.parseInt(mainPageSalePriceFont) >= 700);
            assertTrue(Integer.parseInt(childPageSalePriceFont) >= 700);

            //10д
            assertTrue(mainPageSalePriceDimensions.height > mainPagePriceDimensions.height);
            assertTrue(mainPageSalePriceDimensions.width > mainPagePriceDimensions.width);

            assertTrue(childPageSalePriceDimensions.height > childPagePriceDimensions.height);
            assertTrue(childPageSalePriceDimensions.width > childPagePriceDimensions.width);
        }

    }

    private String getColorCode(String color, int colorIndex) throws Error {
        String code = null;

        Pattern p = Pattern.compile("(\\d+)\\D+(\\d+)\\D+(\\d+)");
        Matcher matcher = p.matcher(color);
        try {
            if (matcher.find()) {
                code = matcher.group(colorIndex);
            }

        } catch (Exception e) {
            throw new Error("Cannot find any correlative color code" + e.getMessage());
        }
        return code;
    }
}
