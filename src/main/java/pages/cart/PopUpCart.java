package pages.cart;

import models.OrderLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class PopUpCart extends BasePage {

    @FindBy(css = ".product-name")
    private WebElement productName;
    @FindBy(css = ".product-price")
    private WebElement productPrice;
    @FindBy(css = ".product-quantity > strong")
    private WebElement productQuantity;
    @FindBy(css = "span[class='subtotal value']")
    private WebElement totalPrice;
    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingButton;


    public PopUpCart(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        waitToBeClickAble(continueShoppingButton);
        return productName.getText();
    }
    public double getProductPrice() {
        waitToBeClickAble(continueShoppingButton);
        return Double.parseDouble(productPrice.getText().replace(System.getProperty("currency"), ""));
    }
    public int getQuantity() {
        waitToBeClickAble(continueShoppingButton);
        return Integer.parseInt(productQuantity.getText());
    }
    public double getTotalPrice() {
        waitToBeClickAble(continueShoppingButton);
        return Double.parseDouble(totalPrice.getText().replace(System.getProperty("currency"), ""));
    }
    public void continueShopping(){
        clickOnElement(continueShoppingButton);
    }
    public OrderLine getOrderLine(){
        return new OrderLine(new Product(getProductName(),getProductPrice()),getQuantity(), getTotalPrice());
    }

}
