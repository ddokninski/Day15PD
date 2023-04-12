package pages.cart;

import models.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#cart-subtotal-products span:nth-of-type(2)")
    private WebElement totalProductsPrice;
    @FindBy(css = "cart-subtotal-shipping")
    private WebElement totalShippingCost;
    @FindBy(css = "div[class='cart-summary-line cart-total'] span:nth-of-type(2)")
    private WebElement totalCostProductsAndShipping;
    @FindBy(css = ".cart-items li:nth-of-type(1) .remove-from-cart")
    private WebElement removeFromCartButton;
    @FindBy(css = ".no-items")
    private WebElement emptyBasketInfo;
    @FindBy(css = ".cart-item")
    private List<WebElement> cartRows;
    @FindBy(css = ".cart-summary .btn-primary")
    private WebElement proceedToCheckOutButton;

    public Order toOrder() {
        Order order = new Order();

        for (WebElement cartRow : cartRows) {
            order.addOrderLine(new CartRowPage(driver, cartRow).toOrderLine());
        }
        return order;
    }

    public double getTotalProductsPrice() {
        return getPrice(totalProductsPrice);
    }

    public CartPage removeFirstProductFromCart() throws InterruptedException {
        Thread.sleep(1000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart-items li:nth-of-type(1) .remove-from-cart")));
        clickOnElement(removeFromCartButton);
        return this;
    }

    public String getTextFromEmptyBasket() throws InterruptedException {
       wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".no-items")));
        return emptyBasketInfo.getText();
    }

    public void clickOnProceedToCheckoutButton() {
        clickOnElement(proceedToCheckOutButton);
    }
}
