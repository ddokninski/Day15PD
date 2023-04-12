package pages.my_Account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class MyAccount extends BasePage {
    public MyAccount(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#history-link")
    private WebElement orderHistory;

    public void goToOrderHistory() {
        clickOnElement(orderHistory);
    }
}
