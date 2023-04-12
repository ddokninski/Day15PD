package pages.sign_in;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".form-group input[type='email']")
    private WebElement emailInput;

    @FindBy(css = ".form-group input[type='password']")
    private WebElement passwordInput;

    @FindBy(css = "#submit-login")
    private WebElement logInButton;


    public void loginUser(String email, String password) {
        sendKeysAndClear(emailInput, email);
        sendKeysAndClear(passwordInput, password);
        clickOnElement(logInButton);
    }
}


