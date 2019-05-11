package ru.litecart;

import org.junit.Test;

public class ProxyTest extends TestBase {

    @Test
    public  void testProxy(){

        proxy.newHar();
        driver.get("http://selenium2.ru");
        proxy.endHar().getLog().getEntries().forEach(l-> System.out.println(l.getRequest().getUrl()));
    }
}
