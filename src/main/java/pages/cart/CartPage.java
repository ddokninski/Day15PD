package pages.cart;

import models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

//    @FindBy(css = "adfa")
//    private WebElement goToCheckOutButton;
    @FindBy(css = "#cart-subtotal-products span:nth-of-type(2)")
    private WebElement totalProductsPrice;
    @FindBy(css = "cart-subtotal-shipping")
    private WebElement totalShippingCost;
    @FindBy(css = "div[class='cart-summary-line cart-total'] span:nth-of-type(2)")
    private WebElement totalCostProductsAndShipping;
    @FindBy(css = ".remove-from-cart")
    private WebElement removeFromCartButton;
    @FindBy(css = ".no-items")
    private WebElement emptyBasketInfo;
    @FindBy(css = ".cart-item")
    private List<WebElement> cartRows;


//    public CheckoutAddressPage goToCheckOut() {
//        clickOnElement(goToCheckOutButton);
//        return new CheckoutAddressPage(driver);
//    }

    public Order toOrder() {
        Order order = new Order();

        for(WebElement cartRow : cartRows){
            order.addOrderLine(new CartRowPage(driver, cartRow).toOrderLine());
        }
        return order;
    }

    public double getTotalProductsPrice() {
        return getPrice(totalProductsPrice);
    }

    public CartPage removeFirstProductFromCart() {
        clickOnElement(removeFromCartButton);
        return this;
    }
    public String getTextFromEmptyBasket() throws InterruptedException {
//        waitToBeClickAble(removeFromCartButton);
        return emptyBasketInfo.getText();
    }


}
