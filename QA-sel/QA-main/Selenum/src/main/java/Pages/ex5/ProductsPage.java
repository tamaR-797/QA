package Pages.ex5;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProductsPage {
    WebDriver driver;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(String productName) {
        String xpathExpression = "//*[contains(@class,'inventory_item')][.//div[contains(@class,'inventory_item_name') and text()='" + productName + "']]//button";
        driver.findElement(By.xpath(xpathExpression)).click();
    }
    public int getCartCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void goToCart() {
        cartButton.click();
    }
}