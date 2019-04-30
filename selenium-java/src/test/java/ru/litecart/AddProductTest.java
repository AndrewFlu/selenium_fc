package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddProductTest extends TestBase {

    @Test
    public void testNewProductAddition() throws InterruptedException {
        String user = "admin";
        String password = "admin";

        driver.get("http://localhost/litecart/admin/login.php");
        login(user, password);

        goToCatalogPage();
        initAddNewProduct();
        tabGeneral();
        fillGeneral();
        tabInformation();
        fillInformation();
        tabPrices();
        fillPrices();
        saveNewProduct();
    }

    private void tabGeneral() throws InterruptedException {
        driver.findElement(By.cssSelector("ul.index li:nth-of-type(1)")).click();
        Thread.sleep(1000);
    }

    private void tabInformation() throws InterruptedException {
        driver.findElement(By.cssSelector("ul.index li:nth-of-type(2)")).click();
        Thread.sleep(1000);
    }

    private void tabPrices() throws InterruptedException {
        driver.findElement(By.cssSelector("ul.index li:nth-of-type(4)")).click();
        Thread.sleep(1000);
    }

    private void fillPrices() {
        final WebElement priceInput = driver.findElement(By.cssSelector("input[name='purchase_price']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '3')", priceInput);

        final WebElement priceSelect = driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']"));
        Select price = new Select(priceSelect);
        price.selectByValue("USD");

        final WebElement taxSelect = driver.findElement(By.cssSelector("select[name='tax_class_id']"));
        Select tax = new Select(taxSelect);
        tax.selectByValue("");

        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("25.00");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("20.00");


    }

    private void fillInformation() {
        WebElement manufacturerSelect = driver.findElement(By.cssSelector("select[name='manufacturer_id']"));
        Select manufacturer = new Select(manufacturerSelect);
        manufacturer.selectByValue("1");

        WebElement supplierSelect = driver.findElement(By.cssSelector("select[name='supplier_id']"));
        Select supplier = new Select(supplierSelect);
        supplier.selectByValue("");

        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("Avengers, Marvel");

        driver.findElement(By.cssSelector("input[name*='short_description']")).sendKeys("Avengers Dug");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Premium anniversary edition by Marvel LLC");

        driver.findElement(By.cssSelector("input[name*='head_title']")).sendKeys("Avengers Dug by Marvel");
        driver.findElement(By.cssSelector("input[name*='meta_description']")).sendKeys("Marvel");
    }

    private void fillGeneral() {
        final WebElement radio = driver.findElement(By.cssSelector("input[name='status'][value='1']"));
        radio.click();
        driver.findElement(By.cssSelector("input[name*='name']")).sendKeys("Avengers Dug");
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys(String.valueOf(System.currentTimeMillis()));
        driver.findElement(By.cssSelector("input[name='categories[]'][ value='1']")).click();
        driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-3']")).click();
        final WebElement quantityEl = driver.findElement(By.cssSelector("input[name='quantity']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '5')", quantityEl);
        final String filePath = System.getProperty("user.dir") + "/src/test/resources/avengers.jpeg";
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(filePath);
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01-04-2019");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("30-04-2019");

    }

    private void initAddNewProduct() {
        driver.findElement(By.cssSelector("td#content a.button:nth-of-type(2)")).click();
    }

    private void goToCatalogPage() {
        driver.findElement(By.cssSelector("td#sidebar li:nth-of-type(2)")).click();
    }

    private void saveNewProduct() {
        driver.findElement(By.cssSelector("button[name='save']")).click();
    }
}
