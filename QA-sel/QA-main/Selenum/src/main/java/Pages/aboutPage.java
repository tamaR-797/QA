package Pages;

import lombok.Getter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class aboutPage {
    WebDriver driver;

    @FindBy(id="firstname")
    WebElement firstName;
    @FindBy(css="#lastname")
    WebElement lastName;
    @FindBy (id="email")
    WebElement email;
    @FindBy (id="next")
    WebElement btnNext;
    @FindBy (xpath = "//*[@id='account']/h4")
    WebDriverWait titlePage;
    @FindBy(id = "Intermediate")
    private WebElement levelIntermediate;

//    @FindBy(id = "next")
//    private WebElement btnNext;

    @FindBy(id = "streetname")
    private WebElement streetName;

    @FindBy(id = "streetnumber")
    private WebElement streetNumber;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "country")
    private WebElement countryCombo;

    @FindBy(id = "finish")
    private WebElement btnFinish;

    @FindBy(id = "messageDiv")
    private WebElement successMessage;
    @FindBy(id = "email-error")
    private WebElement emailError;
    @FindBy(id = "firstname-error")
    private WebElement firstNameError;

    @FindBy(id = "lastname-error")
    private WebElement lastNameError;

    public void AddressAndFinishPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName) {
        Select select = new Select(countryCombo);
        select.selectByVisibleText(countryName);
    }


    public aboutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
