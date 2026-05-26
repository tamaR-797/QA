package Pages.ex5;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CheckoutCompletePage {
    WebDriver driver;

    @FindBy(className = "complete-header")
    private WebElement thankYouHeader;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isThankYouMessageDisplayed() {
        try {
            return thankYouHeader.isDisplayed() && thankYouHeader.getText().equals("Thank you for your order!");
        } catch (Exception e) {
            return false;
        }
    }
}
