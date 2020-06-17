package ru.barancev.seleniumb15.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Assignment4_InternetExplorer {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();
        driver=new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,10);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @Test
    public void myFirstTest(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }
}
