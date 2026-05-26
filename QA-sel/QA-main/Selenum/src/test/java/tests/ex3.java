package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ex3 {
    static  WebDriver  driver;
    @BeforeAll
    public static void BeforeAll(){
        driver = new ChromeDriver();
        driver.get("http://google.com");
        driver.manage().window().maximize();
        System.out.println("BeforeAll");
    }
    @AfterAll
    public static void AfterAll() {
        driver.quit();

    }




    @Test
    public void e2eScenario () throws InterruptedException {

//        Thread.sleep(400);
        driver.navigate().to("https://automation.co.il/tutorials/selenium/demo1/indexID.html");
        WebElement fname=driver.findElement(By.id("firstname"));
        fname.sendKeys("Tamar");
        WebElement lname=driver.findElement(By.id("lastname"));
        lname.sendKeys("Rotan");
        WebElement Email=driver.findElement(By.id("email"));
        Email.sendKeys("tamar@email.com");
        WebElement btnNext=driver.findElement(By.id("next"));
        btnNext.click();
        WebElement LevelIntermediate=driver.findElement(By.id("Intermediate"));
        LevelIntermediate.click();
        WebElement btnNext2=driver.findElement(By.id("next"));
        btnNext2.click();
        driver.findElement(By.id("streetname")).sendKeys("רחוב הריטבא");
        driver.findElement(By.id("streetnumber")).sendKeys("14");
        driver.findElement(By.id("city")).sendKeys("מודיעין עילית");
        WebElement combo = driver.findElement(By.id("country"));
        Select selectCountry = new Select(combo);
        selectCountry.selectByVisibleText("Argentina");
        selectCountry.selectByValue("Italy");
        selectCountry.selectByIndex(2);
        WebElement btnFinish=driver.findElement(By.id("finish"));
        btnFinish.click();
        WebElement title=driver.findElement(By.id("messageDiv"));

        Assertions.assertTrue( title.getText().contains("כל הכבוד אם הצלחת להריץ אותו בעזרת אוטומציה"));



    }
    @Test
    public void  EmailEroor () throws InterruptedException {

        driver.navigate().to("https://automation.co.il/tutorials/selenium/demo1/indexID.html");

        WebElement fname=driver.findElement(By.id("firstname"));
        fname.sendKeys("Tamar");
        WebElement lname=driver.findElement(By.id("lastname"));
        lname.sendKeys("Rotan");
        WebElement Email=driver.findElement(By.id("email"));
        Email.sendKeys("tamar@email.com");
        WebElement btnNext=driver.findElement(By.id("next"));
        btnNext.click();
        WebElement EmailError=driver.findElement(By.id("email-error"));
        System.out.println("Error found: " + EmailError.getText());
        Email.clear();
        Email.sendKeys("tamar@email.com");

        btnNext.click();
        WebElement Email_Error=driver.findElement(By.id("email-error"));
        System.out.println("Error found: " + EmailError.getText());
        Email.clear();
        Email.sendKeys("tamar@email.com");
        btnNext.click();
    }



}
