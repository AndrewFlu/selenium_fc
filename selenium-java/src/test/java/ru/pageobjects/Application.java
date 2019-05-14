package ru.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private CartPage cartPage;
    private ProductPage productPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
    }

    public void quit() {
        driver.quit();
    }


    public void goToMainPage() {
        mainPage.open();
    }

    public void returnToMainPage() {
        driver.findElement(By.cssSelector("header div#logotype-wrapper")).click();
    }

    public void getProductToCart(int quantity) throws InterruptedException {

        for (int i = 0; i < quantity; i++) {
            chooseFirstProductInMainPage();
            productPage.addToCart(i);
            returnToMainPage();
        }
    }

    public void goToCart() {
        mainPage.cart.click();
    }

    public void deleteEveryProductInCart(int quantity) {
        goToCart();


        while (cartPage.tableData.size() > 0) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name='cart_form'] button[value='Remove']")));
            cartPage.removeButton.click();
            wait.until(ExpectedConditions.stalenessOf(cartPage.tableData.get(0)));
        }
        returnToMainPage();
    }

    public int checkoutCart() {
        WebElement cart = cartPage.cartDiv;
        return Integer.parseInt(cart.findElement(By.cssSelector("span.quantity")).getText());
    }

    public void chooseFirstProductInMainPage() {
        final List<WebElement> products = mainPage.allProducts;
        products.get(0).click();
    }


}
