package com.selenium;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static WebDriver driver;
    public static String url = "http://www.flipkart.com";
    public static String driverPath = "C:\\Selenium\\";
    public static String sKey = "Teddy Bear Colors Pink";

    public static void initWebDriver() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void flipkartLogin() {
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")).sendKeys("username");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[4]/button")).click();
    }

    public static void searchItem() {
        driver.findElement(By.name("q")).sendKeys(sKey);
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div[2]/form/div/button")).click();
        threadSleep(5);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/div/a[1]")).click();
    }

    public static void CheckOut() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[2]/form/button")).click();
    }

    public static void threadSleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void endSession() {
        driver.close();
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        initWebDriver();
        flipkartLogin();
        threadSleep(5);
        searchItem();
        threadSleep(5);
        CheckOut();
        threadSleep(20);
        endSession();
    }
}