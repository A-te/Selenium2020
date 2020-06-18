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

public class Assignment8 extends assignmentHelper {
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
        driver.get("http://localhost/litecart");
        List<WebElement> elements = driver.findElements(By.cssSelector("div.content div.content ul li a.link"));
        MatcherAssert.assertThat(elements.size(), CoreMatchers.equalTo(11));
        for (WebElement element : elements){
            List<WebElement>stickers=element.findElements(By.cssSelector(".sticker"));
            MatcherAssert.assertThat(stickers.size(), CoreMatchers.equalTo(1));
        }
    }
    

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }


}
