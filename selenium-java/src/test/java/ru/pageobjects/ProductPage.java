package ru.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "div#cart span")
    public WebElement quantityInCart;

    public void addToCart(int productQuantity) throws InterruptedException {
        productQuantity += 1;
        if (isElementsPresent(By.cssSelector("select[required='required']"))) {
            fillRequiredSelector();
        }
        driver.findElement(By.cssSelector("div#box-product button")).click();
        wait.until(ExpectedConditions.attributeToBe(quantityInCart, "textContent", String.valueOf(productQuantity)));
    }

    public void fillRequiredSelector() {
        final List<WebElement> selectors = driver.findElements(By.cssSelector("select[required='required']"));
        for (WebElement selector : selectors) {
            Select select = new Select(selector);
            select.selectByIndex(1);
        }
    }
}
