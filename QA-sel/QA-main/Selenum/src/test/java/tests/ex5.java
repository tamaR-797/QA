package tests;

import Pages.ex5.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;

public class ex5 {
    public WebDriver driver;
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;
    public CheckoutInformationPage infoPage;
    public CheckoutOverviewPage overviewPage;
    public CheckoutCompletePage completePage;

    @BeforeEach
    public void beforeEach(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        infoPage = new CheckoutInformationPage(driver);
        overviewPage = new CheckoutOverviewPage(driver);
        completePage = new CheckoutCompletePage(driver);
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void e2e() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
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
        infoPage.enterInformation("תמר", "רותן", "123456");
        Thread.sleep(1000);
        infoPage.clickContinue();
        Thread.sleep(1000);
        overviewPage.clickFinish();
        Thread.sleep(1000);

        boolean isSuccessMessageVisible = completePage.isThankYouMessageDisplayed();
        Assertions.assertTrue(isSuccessMessageVisible, "הודעת ההצלחה 'Thank you for your order!' לא הופיעה על המסך!");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void testLoginFromCSV(String loginName, String password, String url) {
        driver.get(url);

        loginPage.login(loginName, password);

        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "ההתחברות נכשלה עבור המשתמש: " + loginName);
    }

    @Test
    public void testProductSortingByPrice() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");

        productsPage.selectSortOption("Price (low to high)");
        Thread.sleep(1000);

        List<Double> actualPrices = productsPage.getProductPrices();
        Thread.sleep(1000);

        for (int i = 0; i < actualPrices.size() - 1; i++) {
            Assertions.assertTrue(actualPrices.get(i) <= actualPrices.get(i + 1),
                    "המיון נכשל! המחיר " + actualPrices.get(i) + " מופיע לפני המחיר " + actualPrices.get(i + 1));
        }
    }
}