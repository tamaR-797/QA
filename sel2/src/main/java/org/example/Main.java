package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args)throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        driver.manage().window().maximize();
//        Thread.sleep(400);
        driver.navigate().to("https://automation.co.il/tutorials/selenium/demo1/indexID.html");
        Thread.sleep(500);
        WebElement fname=driver.findElement(By.id("firstname"));
        fname.sendKeys("Tamar");
        Thread.sleep(1000);
        WebElement lname=driver.findElement(By.id("lastname"));
        lname.sendKeys("Rotan");
        Thread.sleep(1000);
        WebElement Email=driver.findElement(By.id("email"));
        Email.sendKeys("tamar797");
        Thread.sleep(1000);
        WebElement btnNext=driver.findElement(By.id("next"));
        btnNext.click();
        Thread.sleep(1000);
        WebElement EmailError=driver.findElement(By.id("email-error"));
        System.out.println("Error found: " + EmailError.getText());
        Thread.sleep(1000);
        Email.clear();
        Thread.sleep(1000);
        Email.sendKeys("tamar797@email.com");
        Thread.sleep(1000);
        btnNext.click();
        Thread.sleep(1000);
        WebElement LevelIntermediate=driver.findElement(By.id("Intermediate"));
        LevelIntermediate.click();
        Thread.sleep(1000);
        WebElement btnNext2=driver.findElement(By.id("next"));
        btnNext2.click();
        Thread.sleep(1000);
        WebElement streetname= driver.findElement(By.id("streetname"));
        streetname.clear();
        streetname.sendKeys("רחוב ריטבא ");
        Thread.sleep(1000);
        driver.findElement(By.id("streetnumber")).sendKeys("14");
        Thread.sleep(1000);
        driver.findElement(By.id("city")).sendKeys("מודיעין עילית");
        Thread.sleep(1000);
        WebElement combo = driver.findElement(By.id("country"));
        Select selectCountry = new Select(combo);
        selectCountry.selectByVisibleText("Argentina");
        Thread.sleep(1000);
        selectCountry.selectByValue("Italy");
        Thread.sleep(1000);
        selectCountry.selectByIndex(2);
        Thread.sleep(1000);
        WebElement btnFinish=driver.findElement(By.id("finish"));
        btnFinish.click();
        Thread.sleep(1000);
        if (driver.getPageSource().contains("כל הכבוד אם הצלחת להריץ אותו בעזרת אוטומציה")) {
            System.out.println("Test Passed: כל הכבוד אם הצלחת להריץ אותו בעזרת אוטומציה screen is displayed!");
        } else {
            System.out.println("Test Failed: כל הכבוד אם הצלחת להריץ אותו בעזרת אוטומציה screen not found.");
        }
        Thread.sleep(2000);

        driver.quit();
    }
}