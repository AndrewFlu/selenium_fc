package ru.pageobjects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest extends TestBase {

    @Test
    public void testCart() throws InterruptedException {

        int productQuantityToCart = 3;

        app.goToMainPage();
        app.getProductToCart(productQuantityToCart);

        int goodsInCart = app.checkoutCart();

        assertEquals(productQuantityToCart, goodsInCart);
        app.deleteEveryProductInCart(goodsInCart);

        assertEquals(0, app.checkoutCart());
    }

}