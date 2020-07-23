package ru.barancev.seleniumb15.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.barancev.seleniumb15.helpers.assignmentHelper;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Assignment10 extends assignmentHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private Object List;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();
        driver=new FirefoxDriver();
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,5);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @Test
    //а) на главной странице и на странице товара совпадает текст названия товара
    public void sameTextTest(){
        logIn();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));
        String nameMainPage = driver.findElement(By.cssSelector("div#box-campaigns div.content div.name"))
                .getText();
        System.out.println(nameMainPage);
        driver.findElement(By.cssSelector("div#box-campaigns div.content div.name")).click();
        String namePDP = driver.findElement(By.cssSelector("div#page div.content h1.title ")).getText();
        System.out.println(namePDP);
        Assert.assertEquals(nameMainPage,namePDP);
    }


    @Test
    //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
    public void samePriceTest(){
        logIn();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));
        String priceMainPage = driver.findElement(By.cssSelector("div#box-campaigns div.content strong.campaign-price"))
                .getText();
        System.out.println(priceMainPage);
        driver.findElement(By.cssSelector("div#box-campaigns div.content div.name")).click();
        String pricePDP = driver.findElement(By.cssSelector("div#page div.content strong.campaign-price "))
                .getText();
        System.out.println(pricePDP);
        Assert.assertEquals(priceMainPage,pricePDP);
    }

    @Test
    //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой,
    // у которого в RGBa представлении одинаковые значения для каналов R, G и B)
    public void grayCrossedFontTest(){
        logIn();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));

        String textStyleMainPage = driver.findElement(By.cssSelector("div#box-campaigns div.content " +
                "s.regular-price")).getCssValue("text-decoration-line");
        String textColorMainPage = driver.findElement(By.cssSelector("div#box-campaigns div.content " +
                "s.regular-price")).getCssValue("color");
        System.out.println(textColorMainPage);
        System.out.println(textStyleMainPage);
        System.out.println(isGreyFirefox(textColorMainPage));
        Assert.assertEquals(true,isGreyFirefox(textColorMainPage));
        System.out.println(textStyleMainPage);
        driver.findElement(By.cssSelector("div#box-campaigns div.content div.name")).click();
        String textStylePDP = driver.findElement(By.cssSelector("div#page div.content s.regular-price "))
                .getCssValue("text-decoration-line");
        String textColorPDP = driver.findElement(By.cssSelector("div#page div.content s.regular-price "))
                .getCssValue("color");
        System.out.println(textColorPDP);
        System.out.println(isGreyFirefox(textColorPDP));
        Assert.assertEquals(true,isGreyFirefox(textColorPDP));
        System.out.println(textStylePDP);
        Assert.assertEquals("line-through",textStyleMainPage);
        Assert.assertEquals(textStyleMainPage,textStylePDP);
    }



    @Test
    //г) акционная жирная и красная (можно считать, что "красный" цвет это такой,
    // у которого в RGBa представлении каналы G и B имеют нулевые значения)
    //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
    public void boldRedTextTest(){
        logIn();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));
        String colorPriceMainPage = driver.findElement(By.cssSelector("div#box-campaigns div.content strong.campaign-price"))
                .getCssValue("color");
        System.out.println(colorPriceMainPage);
        driver.findElement(By.cssSelector("div#box-campaigns div.content div.name")).click();
        String colorPricePDP = driver.findElement(By.cssSelector("div#page div.content strong.campaign-price "))
                .getCssValue("color");
        System.out.println(colorPricePDP);
        Assert.assertEquals(true,isRedFirefox(colorPriceMainPage));
        Assert.assertEquals(true,isRedFirefox(colorPricePDP));
    }


    @Test
    //д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
    public void isBiggerTextTest(){
        logIn();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));
        String regularSizePriceMP = driver.findElement(By.cssSelector("div#box-campaigns div.content " +
                "s.regular-price")).getCssValue("font-size");
        String discountSizePriceMP = driver.findElement(By.cssSelector
                ("div#box-campaigns div.content strong.campaign-price")).getCssValue("font-size");
        System.out.println(regularSizePriceMP +" " +regularSizePriceMP.replace("px",""));
        System.out.println(discountSizePriceMP +" " + discountSizePriceMP.replace("px",""));
        driver.findElement(By.cssSelector("div#box-campaigns div.content div.name")).click();
        String regularSizePricePDP = driver.findElement(By.cssSelector("div#page div.content s.regular-price "))
                .getCssValue("font-size");
        String discountSizePricePDP = driver.findElement(By.cssSelector("div#page div.content strong.campaign-price "))
                .getCssValue("font-size");
        System.out.println(regularSizePricePDP +" " + regularSizePricePDP.replace("px",""));
        System.out.println(discountSizePricePDP +" " + discountSizePriceMP.replace("px",""));
        //int rsMP=Integer.parseInt(regularSizePriceMP.replaceAll("([a-z])", ""));
        double rsMP = Double.parseDouble(regularSizePriceMP.replaceAll("([a-z])", ""));
        //int dsMP=Integer.parseInt(discountSizePriceMP.replaceAll("([a-z])", ""));
        double dsMP=Double.parseDouble(discountSizePriceMP.replaceAll("([a-z])", ""));
        //int rsPDP=Integer.parseInt(regularSizePricePDP.replaceAll("([a-z])", ""));
        double rsPDP=Double.parseDouble(regularSizePricePDP.replaceAll("([a-z])", ""));
        //int dsPDP=Integer.parseInt(discountSizePriceMP.replaceAll("([a-z])", ""));
        double dsPDP=Double.parseDouble(discountSizePriceMP.replaceAll("([a-z])", ""));
        Assert.assertTrue(rsMP<dsMP);
        Assert.assertTrue(rsPDP<dsPDP);
        //Assert.assertEquals(true,isRedFirefox(sizePriceMainPage));
        //Assert.assertEquals(true,isRedFirefox(colorPricePDP));
    }


    public boolean isRedFirefox(String color){
//        String color2 = color.replaceAll("([a-z])","").replace("(","")
//                .replace(")","");
        String color2 = color.replaceAll("([a-z])","").replaceAll("([()])","");
        System.out.println(color2);
        String[] arrSplit= color2.split(", ");
        System.out.println(arrSplit[0]);
        System.out.println(arrSplit[1]);
        System.out.println(arrSplit[2]);
        int r=Integer.parseInt(arrSplit[0]);
        int g=Integer.parseInt(arrSplit[1]);
        int b=Integer.parseInt(arrSplit[2]);
        System.out.println(r+" "+g+" "+b);

        if(g==0 && b==0){
            return true;
        }
        return false;
    }





    public boolean isGreyFirefox(String color){
        String color2 = color.replaceAll("([a-z])","").replace("(","").replace(")","");
        System.out.println(color2);
        String[] arrSplit= color2.split(", ");
        System.out.println(arrSplit[0]);
        System.out.println(arrSplit[1]);
        System.out.println(arrSplit[2]);
        int r=Integer.parseInt(arrSplit[0]);
        int g=Integer.parseInt(arrSplit[1]);
        int b=Integer.parseInt(arrSplit[2]);
        System.out.println(r+" "+g+" "+b);

        if(r-g==0 && r-b==0){
            return true;
        }
        return false;
    }





    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void logIn() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }


    @After
    public void stop(){
        driver.quit();
        driver=null;
    }


}
