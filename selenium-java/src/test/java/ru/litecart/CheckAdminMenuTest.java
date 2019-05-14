package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckAdminMenuTest extends AdminLoginTest.TestBase {

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

    public static class CartTest extends AdminLoginTest.TestBase {

        @Test
        public void testCart() throws InterruptedException {
            driver.get("http://localhost/litecart/en/");
            int productToCart = 0;

            chooseFirstProductInMainPage();
            productToCart = addToCart(productToCart);
            returnToMainPage();

            chooseFirstProductInMainPage();
            productToCart = addToCart(productToCart);
            returnToMainPage();

            chooseFirstProductInMainPage();
            productToCart = addToCart(productToCart);
            returnToMainPage();

            final int goodsInCart = checkoutCart();

            assertEquals(productToCart, goodsInCart);

            goToCart();
            deleteEveryProductInCart(goodsInCart);
            returnToMainPage();

            assertTrue(0 == checkoutCart());
        }

        private void goToCart() {
            driver.findElement(By.cssSelector("div#cart a.link")).click();
        }

        private void deleteEveryProductInCart(int quantity) {
            List<WebElement> tableData = driver.findElements(By.cssSelector("table.dataTable tr"));

            while (tableData.size() > 0) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name='cart_form'] button[value='Remove']")));
                driver.findElement(By.cssSelector("form[name='cart_form'] button[value='Remove']")).click();
                wait.until(ExpectedConditions.stalenessOf(tableData.get(0)));
                tableData = driver.findElements(By.cssSelector("table.dataTable tr"));
            }

        }

        private int checkoutCart() {
            WebElement cart = driver.findElement(By.cssSelector("div#cart"));
            return Integer.parseInt(cart.findElement(By.cssSelector("span.quantity")).getText());
        }

        private void returnToMainPage() {
            driver.findElement(By.cssSelector("header div#logotype-wrapper")).click();
        }

        private int addToCart(int productQuantity) throws InterruptedException {
            productQuantity += 1;
            WebElement quantityInCart = driver.findElement(By.cssSelector("div#cart span"));
            if (isElementsPresent(By.cssSelector("select[required='required']"))) {
                fillRequiredSelector();
            }
            driver.findElement(By.cssSelector("div#box-product button")).click();
            wait.until(ExpectedConditions.attributeToBe(quantityInCart, "textContent", String.valueOf(productQuantity)));
            return productQuantity;
        }

        private boolean isElementsPresent(By locator) {
            final List<WebElement> elements = driver.findElements(locator);
            return elements.size() > 0;
        }

        private void fillRequiredSelector() {
            final List<WebElement> selectors = driver.findElements(By.cssSelector("select[required='required']"));
            for (WebElement selector : selectors) {
                Select select = new Select(selector);
                select.selectByIndex(1);
            }
        }

        private void chooseFirstProductInMainPage() {
            final List<WebElement> products = driver.findElements(By.cssSelector("div.content li.product"));
            products.get(0).click();
        }

    }
}
