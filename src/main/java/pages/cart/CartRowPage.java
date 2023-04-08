package pages.cart;

import models.OrderLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class CartRowPage extends BasePage {
    public CartRowPage(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = ".product-line-info a")
    private WebElement productName;

    @FindBy(css = ".current-price .price")
    private WebElement productPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement productQuantity;

    @FindBy(css = ".product-price strong")
    private WebElement productTotalPrice;

    public String getProductName() {
        return productName.getText();
    }

    public Product toProduct() {
        return new Product(productName.getText(),
                getPrice(productPrice));
    }

    public OrderLine toOrderLine() {
        return new OrderLine(toProduct(),
                Integer.parseInt(productQuantity.getAttribute("value")),
                getPrice(productTotalPrice));
    }
}
