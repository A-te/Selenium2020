package ru.barancev.seleniumb15.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.barancev.seleniumb15.helpers.assignmentHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Assignment7 extends assignmentHelper {
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
    }

    @Test
    public void listOfElementsTest(){
        logIn();
        wait.until(titleIs("My Store"));
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"app-\"]"));
        MatcherAssert.assertThat(elements.size(), CoreMatchers.equalTo(17));
        clickMenuList(elements);
    }

    private void clickMenuList(List<WebElement>elms) {
        elms = driver.findElements(By.xpath("//*[@id=\"app-\"]"));
        for (int i=0;i<elms.size();i++) {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"app-\"]"));
            elements.get(i).click();
            boolean result = isElementPresent(driver,By.cssSelector("h1"));
            assert result == true;
            List<WebElement> elms1 = driver.findElements(By.cssSelector("ul.docs li > a[href]>span.name"));
            for(int j=0;j<elms1.size();j++){
                List<WebElement>elements1= driver.findElements(By.cssSelector("ul.docs li > a[href]>span.name"));
                elements1.get(j).click();
                boolean result1 = isElementPresent(driver,By.cssSelector("h1"));
                assert result1 == true;
            }
        }
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    @After
    public void stop(){
        driver.quit();
        driver=null;
    }

    public void logIn() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
