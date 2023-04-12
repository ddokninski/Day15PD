package pages.my_Account;

import models.OrderHistoryLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class OrderRowPage extends BasePage {
    public OrderRowPage(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = "th")
    private WebElement orderNumber;
    @FindBy(xpath = "./td[1]")
    private WebElement date;
    @FindBy(xpath = "./td[2]")
    private WebElement totalPrice;
    @FindBy(xpath = "./td[3]")
    private WebElement payment;
    @FindBy(xpath = "./td[4]")
    private WebElement status;
    @FindBy(xpath = "./td[5]")
    private WebElement invoice;
    @FindBy(xpath = "./td[6] //a[1]")
    private WebElement detailsButton;

    public String getOrderNumber() {
        return orderNumber.getText();
    }

    public String getOrderDate() {
        return date.getText();
    }

    public double getTotalPrice() {
        return getPrice(totalPrice);
    }

    public String getStatusOfPayment() {
        return status.getText();
    }

    public void goToOrderDetails() {
        clickOnElement(detailsButton);
    }

    public OrderHistoryLine toOrderHistoryLine(OrderRowPage row) {
        return new OrderHistoryLine(getOrderNumber(), getOrderDate(), getTotalPrice(), getStatusOfPayment());
    }
}
