package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ProductMiniaturePage extends BasePage {
    public ProductMiniaturePage(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = ".product-title")
    private WebElement title;

    @FindBy(css = ".price")
    private WebElement price;

    public String getTitle() {
        return title.getText();
    }

    public double getPrice() {
        return getPrice(price);
    }

    public void open() {
        clickOnElement(title);
    }
}
