package ru.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {



    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/checkout");
    }



    @FindBy (css = "form[name='cart_form'] button[value='Remove']")
    public WebElement removeButton;

    @FindBy (css = "table.dataTable tr")
    public List<WebElement> tableData;

    @FindBy (css = "div#cart")
    public WebElement cartDiv;

    @FindBy (css = "header div#logotype-wrapper")
    public WebElement logo;

    public void deleteEveryProductInCart(int quantity) {

        open();

        while (tableData.size() > 0) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name='cart_form'] button[value='Remove']")));
            removeButton.click();
            wait.until(ExpectedConditions.stalenessOf(tableData.get(0)));
        }
        returnToMainPage();
    }

    public void returnToMainPage() {
        logo.click();
    }
}
