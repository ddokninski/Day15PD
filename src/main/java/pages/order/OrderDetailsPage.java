package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class OrderDetailsPage extends BasePage {
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "td span[class='label label-pill bright']")
    private WebElement statusOfPayment;
    @FindBy(css = "table[class='table table-striped table-bordered table-labeled hidden-xs-down'] tbody td:first-child")
    private WebElement dateOfOrder;
    @FindBy(css = "#order-products tfoot tr:last-child td:last-child")
    private WebElement totalPrice;
    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;
    @FindBy(css = "#invoice-address")
    private WebElement invoiceAddress;

    public String getStatusOfPayment() {
        return statusOfPayment.getText();
    }

    public String getDateOfOrder() {
        return dateOfOrder.getText();
    }

    public double getTotalPrice() {
        return getPrice(totalPrice);
    }

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public String getInvoiceAddress() {
        return invoiceAddress.getText();
    }
}
