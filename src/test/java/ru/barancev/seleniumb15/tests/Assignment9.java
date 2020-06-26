package ru.barancev.seleniumb15.tests;

import org.checkerframework.checker.units.qual.A;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.barancev.seleniumb15.helpers.assignmentHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.sort;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Assignment9 extends assignmentHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private Object List;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,5);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        logIn();
    }

    @Test
    //а) проверить, что страны расположены в алфавитном порядке
    public void countriesAlphabetical() {

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> elements = driver.findElements(By.cssSelector("tr.row"));
        MatcherAssert.assertThat(elements.size(), CoreMatchers.equalTo(238));
        ArrayList<String> unordered = new ArrayList<>();
        for (WebElement element : elements) {
            String country = element.findElement(By.cssSelector("td a")).getAttribute("textContent");
            System.out.println(country);
            unordered.add(country);
        }
        MatcherAssert.assertThat(unordered.size(), CoreMatchers.equalTo(238));
        MatcherAssert.assertThat(true, CoreMatchers.equalTo(isSorted(unordered)));
    }


    @Test
    //б)для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить,
    // что зоны расположены в алфавитном порядке
    public void countriesZonesAlphabetical() {

        //driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countryWithZones;
        countryWithZones = getWebElements(driver);


        for (int i=0;i<countryWithZones.size();i++) {

            countryWithZones.get(i).findElement(By.cssSelector(".dataTable>tbody tr>td:nth-child(5)>a")).click();
            List<WebElement> listofzones = new ArrayList<>();
            listofzones = null;
            listofzones=driver.findElements(By.cssSelector
                    ("table#table-zones> tbody tr td:nth-child(3)> input[type=hidden]"));
            System.out.println(listofzones.size());
            ArrayList<String> unorderedzones = new ArrayList<>();
            for (WebElement x : listofzones) {
                String zone = x.getAttribute("value");
                unorderedzones.add(zone);
            }
            System.out.println(isSorted(unorderedzones));
            MatcherAssert.assertThat(true, CoreMatchers.equalTo(isSorted(unorderedzones)));

            System.out.println(countryWithZones.size());
            countryWithZones=getWebElements(driver);
            System.out.println(countryWithZones.size());
        }

    }

    @Test
    //2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
    //зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
    public void ZonesAlphabetical(){
        java.util.List<WebElement> elements2 = getWebElements2(driver);
        for (int i=0; i<elements2.size();i++){
            elements2.get(i).click();
            List<WebElement> editzones = new ArrayList<>();
            editzones=driver.findElements(By.cssSelector
                    ("table#table-zones tr td:nth-child(3) > select > option[selected=selected]"));
            System.out.println(editzones.size());
            List<String> xzones = new ArrayList<>();
            for(WebElement zone : editzones){
                String xzone = zone.getAttribute("textContent");
                System.out.println(xzone);
                xzones.add(xzone);
            }
            System.out.println(isSorted(xzones));
            Assert.assertTrue(isSorted(xzones));
            elements2 = getWebElements2(driver);
        }

    }


    public List<WebElement> getWebElements2(WebDriver driver) {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement>elements2 = driver.findElements(By.cssSelector
                ("table.dataTable tr.row td:nth-child(3)>a" ));
        System.out.println(elements2.size());
        return elements2;
    }


    public List<WebElement> getWebElements(WebDriver driver) {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> elements = new ArrayList<>();
        elements=null;
        List<WebElement> countryWithZones = new ArrayList<>();


        elements=driver.findElements(By.cssSelector("table.dataTable tr.row"));
        for (WebElement element : elements) {
            int zones = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(6)"))
                    .getAttribute("innerText"));
            if (zones > 0) {
                String country = element.findElement(By.cssSelector("td a")).getAttribute("textContent");
                System.out.println(country);
                countryWithZones.add(element);
            }
        }
        return countryWithZones;
    }


    public void logIn() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public static boolean isSorted(List<String> list){
        String previous = "";
        for (String current: list) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }
    

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }


}
