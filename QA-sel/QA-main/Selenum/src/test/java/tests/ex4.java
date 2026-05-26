package tests;

import Pages.aboutPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll; // ודאי שזה Jupiter
import org.junit.jupiter.api.Test;      // שונה מ-org.junit.Test
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ex4 {
    public static WebDriver driver;
    public static aboutPage page1;




    @Test
    public void e2e(){

        page1.getFirstName().sendKeys("tamar");
        page1.getLastName().sendKeys("Rotan");
        page1.getEmail().sendKeys("tamar@gmail.com");
        page1.getBtnNext().click();
        page1.getLevelIntermediate().click();
        page1.getBtnNext().click();
        page1.getStreetName().sendKeys("רחוב  הריטבא");
        page1.getStreetNumber().sendKeys("14");
        page1.getCity().sendKeys("מודיעין עילית");
        page1.selectCountry("Argentina");

        page1.getBtnFinish().click();

        String finalMessage = page1.getSuccessMessage().getText();
        Assertions.assertTrue(finalMessage.contains("כל הכבוד אם הצלחת להריץ אותו בעזרת אוטומציה"));
    }
    @Test
    public  void  email_error() {
        page1.getFirstName().sendKeys("Tamar");
        page1.getLastName().sendKeys("Rotan");

        page1.getEmail().sendKeys("tamar@gcom");
        page1.getBtnNext().click();
        String errorText = page1.getEmailError().getText();
        System.out.println("Error found: " + errorText);
        page1.getEmail().clear();
        page1.getEmail().sendKeys("tamar@gmail.com");
        page1.getBtnNext().click();

        Assertions.assertFalse(errorText.isEmpty(), "הודעת השגיאה לא הופיעה!");

    }

    @Test
    public void nameValidationNegativeTest() {
        page1.getFirstName().sendKeys("t");
        page1.getLastName().sendKeys("ro");
        page1.getEmail().sendKeys("tamar@gmail.com"); // אימייל תקין כדי לבודד את שגיאת השמות

        page1.getBtnNext().click();

        String fNameError = page1.getFirstNameError().getText();
        System.out.println("First Name Error: " + fNameError);

        String lNameError = page1.getLastNameError().getText();
        System.out.println("Last Name Error: " + lNameError);

        Assertions.assertEquals("Please enter at least 3 characters.", fNameError);
        Assertions.assertEquals("Please enter at least 3 characters.", lNameError);
    }


}