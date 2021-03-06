package ru.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckSortedCountriesAndTimeZonesTest extends AdminLoginTest.TestBase {

    @Test
    public void testCountiesSortedByAlphabeticalOrder() {

        String username = "admin";
        String password = "admin";
        login(username, password);
        List<String> actualCountries = new ArrayList<>();
        List<String> sortedCountries = new ArrayList<>();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countryElements = driver.findElements(By.cssSelector("table.dataTable tr.row"));

        for (WebElement element : countryElements) {
            final String country = element.findElement(By.cssSelector("td:nth-of-type(5)")).getAttribute("textContent");
            actualCountries.add(country);
        }

        assertTrue(actualCountries.size() > 0);

        sortedCountries.addAll(actualCountries);
        Collections.sort(sortedCountries);

        for (int i = 0; i < actualCountries.size(); i++) {
            assertEquals(sortedCountries.get(i), actualCountries.get(i));
        }
    }

    @Test
    public void testTimeZonesSortedByAlphabeticalOrder() {

        String username = "admin";
        String password = "admin";

        List<String> sortedTimeZones = new ArrayList<>();
        List<String> hrefToTimeZone = new ArrayList<>();

        login(username, password);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countryElements = driver.findElements(By.cssSelector("table.dataTable tr.row"));

        for (WebElement element : countryElements) {

            Integer timeZones = Integer.parseInt(
                    element.findElement(By.cssSelector("td:nth-of-type(6)")).getAttribute("textContent"));

            if (timeZones > 0) {
                String href = element.findElement(By.cssSelector("td:nth-of-type(7) a")).getAttribute("href");
                hrefToTimeZone.add(href);
            }
        }

        for (String url : hrefToTimeZone) {

            List<String> actualTimeZones = new ArrayList<>();
            driver.get(url);
            List<WebElement> timeZoneElements = driver.findElements(By.cssSelector("table#table-zones td:nth-of-type(3) input[type='hidden']"));

            for (WebElement zoneElement : timeZoneElements) {
                actualTimeZones.add(zoneElement.getAttribute("value"));
            }
            sortedTimeZones.clear();
            ;
            sortedTimeZones.addAll(actualTimeZones);
            Collections.sort(sortedTimeZones);

            for (int i = 0; i < actualTimeZones.size(); i++) {
                assertEquals(sortedTimeZones.get(i), actualTimeZones.get(i));
            }
        }
    }

    @Test
    public void testTimeZonesOrderInCountries() {

        String username = "admin";
        String password = "admin";
        login(username, password);
        List<String> countryUrlList = new ArrayList<>();

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> elements = driver.findElements(By.cssSelector("table.dataTable tr.row"));

        for (WebElement element : elements) {
            WebElement country = element.findElement(By.cssSelector("td:nth-of-type(3)"));
            String url = country.findElement(By.cssSelector("a")).getAttribute("href");
            countryUrlList.add(url);
        }

        for (int i = 0; i < countryUrlList.size(); i++) {
            driver.get(countryUrlList.get(i));
            final List<WebElement> selectorsList = driver.findElements(By.cssSelector("table.dataTable td:nth-of-type(3) select"));



            for (WebElement selector : selectorsList) {

                List<String> actualOptions = new ArrayList<>();
                List<String> sortedOptions = new ArrayList<>();

                List<WebElement> options = selector.findElements(By.cssSelector("option"));

                for (WebElement option : options) {
                    actualOptions.add(option.getAttribute("textContent"));
                }

                actualOptions.remove(0);
                sortedOptions.addAll(actualOptions);
                Collections.sort(sortedOptions);
                assertEquals(sortedOptions, actualOptions);
            }
        }
    }
}
