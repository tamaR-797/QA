package tests;

import Pages.ex5.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ex5 {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static ProductsPage productsPage;
    public static CartPage cartPage;
    public static CheckoutInformationPage infoPage;
    public static CheckoutOverviewPage overviewPage;
    public static CheckoutCompletePage completePage;

    @BeforeAll
    public static void BeforeAll() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        infoPage = new CheckoutInformationPage(driver);
        overviewPage = new CheckoutOverviewPage(driver);
        completePage = new CheckoutCompletePage(driver);
    }

    @AfterAll
    public static void AfterAll() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void e2e() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.addProductToCart("Sauce Labs Bolt T-Shirt");
        Thread.sleep(1000);

        int expectedItemsCount = 3;
        Assertions.assertEquals(expectedItemsCount, productsPage.getCartCount(), "המספר שעל העגלה אינו תואם לכמות המוצרים שנבחרו!");
        Thread.sleep(1000);
        productsPage.goToCart();
        Thread.sleep(1000);
        Assertions.assertEquals(expectedItemsCount, cartPage.getCartItemsCount(), "מספר הפריטים שנמצאו ברשימת העגלה אינו תואם!");
        Thread.sleep(1000);
        cartPage.clickCheckout();
        Thread.sleep(1000);
        infoPage.enterInformation("tamar", "rotan", "1234");
        Thread.sleep(1000);
        infoPage.clickContinue();
        Thread.sleep(1000);
        overviewPage.clickFinish();
        Thread.sleep(1000);

        boolean isSuccessMessageVisible = completePage.isThankYouMessageDisplayed();
        Assertions.assertTrue(isSuccessMessageVisible, "הודעת ההצלחה 'Thank you for your order!' לא הופיעה על המסך!");

    }

    @ParameterizedTest(name = "loginTest")
    @CsvFileSource(resources = "./DataTest.csv", numLinesToSkip = 1)
    public void loginTest(String username, String password) {
        System.out.println("loginTest");
        loginPage.login(username, password);
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"), "the login was failed");
    }
}