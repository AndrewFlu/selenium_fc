package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

public class OpenLinksInNewWindowTest extends AdminLoginTest.TestBase {

    @Test
    public void testOpenLinksInNewWindow() {
        String username = "admin";
        String password = "admin";

        driver.get("http://localhost/litecart/admin/login.php");
        login(username, password);
        goToCountriesPage();
        openEditCountryPage();

        final List<WebElement> allExternalLinks = driver.findElements(By.cssSelector("#content a[target=\"_blank\"]"));
        for (WebElement link : allExternalLinks) {
            final Set<String> oldWindows = driver.getWindowHandles();
            final String mainWindow = driver.getWindowHandle();
            link.click();
            final String newWindow = wait.until(thereIsWindowOtherThen(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    private ExpectedCondition<String> thereIsWindowOtherThen(Set<String> oldWindows) {
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

    private void openEditCountryPage() {
        final List<WebElement> countries = driver.findElements(By.cssSelector("tr.row td:nth-of-type(5) a"));
        countries.iterator().next().click();
    }

    private void goToCountriesPage() {
        driver.findElement(By.cssSelector("#box-apps-menu li:nth-of-type(3)")).click();
    }
}
