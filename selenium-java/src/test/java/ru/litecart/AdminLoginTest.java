package ru.litecart;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AdminLoginTest extends TestBase {

    @Test
    public void testLoginInAdministrativeArea() {
        String adminLogin = "admin";
        String adminPassword = "admin";
        driver.get("http://localhost/litecart/admin/login.php");
        login(adminLogin, adminPassword);
        assertTrue(driver.findElement(By.id("box-apps-menu-wrapper")).isDisplayed());
    }

    public static class TestBase {

        public static class MyListener extends AbstractWebDriverEventListener {
            @Override
            public void beforeFindBy(By by, WebElement element, WebDriver driver) {
                System.out.println(by);
            }

            @Override
            public void afterFindBy(By by, WebElement element, WebDriver driver) {
                System.out.println(by + " found");
            }

            @Override
            public void onException(Throwable throwable, WebDriver driver) {
                System.out.println(throwable);
            }
        }


        public EventFiringWebDriver driver;
        public WebDriverWait wait;
        public BrowserMobProxy proxy;

        @Before
        public void start() {

            // start the proxy
            proxy = new BrowserMobProxyServer();
            proxy.start(0);

            // get the Selenium proxy object
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

            // configure it as a desired capability
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
            driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
            driver.register(new MyListener());
            wait = new WebDriverWait(driver, 5);
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.manage().logs().get("browser").getAll();

        }

        protected void login(String username, String password) {
            driver.get("http://localhost/litecart/admin/login.php");
            driver.findElement(By.cssSelector("input[name=username]")).sendKeys(username);
            driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
            driver.findElement(By.cssSelector("button[name=login]")).click();
        }

        protected boolean isElementPresent(By locator) {
            return driver.findElements(locator).size() > 0;
        }

        @After
        public void stop() {
            driver.quit();
            driver = null;
        }
    }
}
