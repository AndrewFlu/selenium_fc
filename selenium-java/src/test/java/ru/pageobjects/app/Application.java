package ru.pageobjects.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pageobjects.pages.CartPage;
import ru.pageobjects.pages.MainPage;
import ru.pageobjects.pages.ProductPage;

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


    public void getProductToCart(int quantity) throws InterruptedException {

        for (int i = 0; i < quantity; i++) {
            chooseFirstProductInMainPage();
            productPage.addToCart(i);
            cartPage.returnToMainPage();
        }
    }

    public void goToCart() {
        mainPage.cart.click();
    }

    public void deleteProductsFromCart(int productsQuantity) {
        cartPage.deleteEveryProductInCart(productsQuantity);
    }

    ;


    public int checkoutCart() {
        WebElement cart = cartPage.cartDiv;
        return Integer.parseInt(cart.findElement(By.cssSelector("span.quantity")).getText());
    }

    public void chooseFirstProductInMainPage() {
        final List<WebElement> products = mainPage.allProducts;
        products.get(0).click();
    }


}
