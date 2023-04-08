package pages.product;

import models.OrderLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ProductDetailsPage extends BasePage {

    @FindBy(css = ".h1")
    private WebElement productName;
    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;
    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;
    @FindBy(css = ".current-price span:nth-of-type(1)")
    private WebElement productPrice;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage setQuantity(int i) {
        sendKeysAndClear(quantityInput, String.valueOf(i));
        return this;
    }

    public void addToCart(){
        clickOnElement(addToCartButton);
    }

    public double getProductPrice(){
        return Double.parseDouble(productPrice.getText().replace(System.getProperty("currency"), ""));
    }

//    public CartPopUpPage addToCart() {
//        return new CartPopUpPage(driver);
//    }

    public String getProductName() {
        return productName.getText();
    }
    public int getQuantity(){
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

//    public Product getProductDetails() {
//        //do konstruktora wsadzić nazwę cenę cenę łączną z tego co widzi na stronie
//        return new Product();
//    }

    public OrderLine getOrderLineDetails() {
        return new OrderLine(new Product(getProductName(), getProductPrice()), getQuantity(), (getProductPrice() * getQuantity()));
    }
}
