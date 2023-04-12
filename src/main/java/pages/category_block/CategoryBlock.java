package pages.category_block;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class CategoryBlock extends BasePage {
    public CategoryBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class='block-category card card-block'] .h1")
    private WebElement categoryName;

    @FindBy(css = "div[class='col-md-6 hidden-sm-down total-products']")
    private WebElement infoAboutQuantityOfProducts;

    public String getCategoryName() {
        return categoryName.getText();
    }

    public String infoAboutQuantityOfProductsOnPage() {
        return infoAboutQuantityOfProducts.getText();
    }
}
