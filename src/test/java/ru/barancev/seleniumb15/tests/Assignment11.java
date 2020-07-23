package ru.barancev.seleniumb15.tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.barancev.seleniumb15.helpers.AccountDataGen;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Assignment11 {
    private WebDriver driver;
    private WebDriverWait wait;
    private AccountDataGen gen11;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,10);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        gen11 = new AccountDataGen();
    }

    @Test
    public void userCreationTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();

        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']//b"))
                .click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']"))
                .sendKeys("United States"+Keys.ENTER);
//        String firstname = gen11.firstname;
//        String lastname = gen11.lastname;
//        String email = gen11.email;


        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(gen11.firstname +Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(gen11.lastname+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("666 Kreml Road"+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("66688"+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Prison"+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(gen11.email+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("6660110"+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("VovaPutyaH"+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("VovaPutyaH"+Keys.ENTER);
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        driver.findElement(By.xpath("//option[contains(text(),'California')]")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("VovaPutyaH"+Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("VovaPutyaH"+Keys.ENTER);
        wait.until(titleIs("Online Store | My Store"));
        driver.findElement(By.xpath("//div[@class='content']//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(gen11.email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("VovaPutyaH");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        List<WebElement> editAccountElement = driver.findElements(By.xpath("//div[@class='content']//a[contains(text(),'Edit Account')]"));
        if(editAccountElement.size() != 0)
            System.out.println("Element present");
        else
            System.out.println("Element not present");


    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }
}
