package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class CreateUserAccountTest extends TestBase {

    @Test
    public void testUserAccountCreation() {
        String login = String.format("rtm+%d@mail.ru", System.currentTimeMillis());
        String password = "UserPassword";

        driver.get("http://localhost/litecart/en/");
        registration(login, password);
        logOut();
        logIn(login, password);
        logOut();
    }

    private void logIn(String login, String password) {
        type(By.cssSelector("form[name='login_form'] input[name='email']"), login);
        type(By.cssSelector("form[name='login_form'] input[name='password']"), password);
        driver.findElement(By.cssSelector("form[name='login_form'] button[name='login']")).click();
    }

    private void logOut() {
        driver.findElement(By.cssSelector("div.content a[href=\"http://localhost/litecart/en/logout\"]")).click();
    }

    private void registration(String login, String password) {
        initRegistration();
        fillRegistrationForm(login, password);
        submitRegistration();
    }

    private void submitRegistration() {
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
    }

    private void fillRegistrationForm(String login, String password) {
        type(By.cssSelector("input[name='firstname']"), "Firstname");
        type(By.cssSelector("input[name='lastname']"), "Lastname");
        type(By.cssSelector("input[name='address1']"), "USA, Address_1");
        type(By.cssSelector("input[name='postcode']"), "12345");
        type(By.cssSelector("input[name='city']"), "Phoenix");


        final WebElement selectorCountryElement = driver.findElement(By.cssSelector("select[name='country_code']"));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].selectedIndex = 224; arguments[0].dispatchEvent(new Event('change'))", selectorCountryElement);

        final WebElement selectorCityElement = driver.findElement(By.cssSelector("select[name='zone_code']"));

        // диапазон индексов городов 0 до 64
        int cityIndex = 0 + (int) (Math.random() * 64);

        ((JavascriptExecutor) driver)
                .executeScript(String.format("arguments[0].selectedIndex = %d", cityIndex), selectorCityElement);

        type(By.cssSelector("input[name='email']"), login);
        type(By.cssSelector("input[name='phone']"), "+79011870155");
        type(By.cssSelector("input[name='password']"), password);
        type(By.cssSelector("input[name='confirmed_password']"), password);
    }

    private void type(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    private void initRegistration() {
        driver.findElement(By.cssSelector("form[name=\"login_form\"] tr:last-child a")).click();
    }
}
