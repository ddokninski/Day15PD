package pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ProductsGridPage extends BasePage {
    public ProductsGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-miniature")
    private List<WebElement> productsMiniatures;


//    public ProductDetailsPage openProductWithName(String someName) {
//        return new ProductDetailsPage(driver);
//    }

    public List<ProductMiniaturePage> getProducts() {
        //jakiś wait na elementy
        List<ProductMiniaturePage> productMiniaturePages = new ArrayList<>();

        for (WebElement element : productsMiniatures) {
            productMiniaturePages.add(new ProductMiniaturePage(driver, element));
        }
        return productMiniaturePages;
    }

    public void openRandomProduct() {
        var product = new ProductMiniaturePage(driver, getRandomElement(productsMiniatures));
        product.open();
    }

    public String getTitleOfRandomProduct() {
        var product = new ProductMiniaturePage(driver, getRandomElement(productsMiniatures));
        String productName = product.getTitle();
//        product.open();
        return productName;
    }

    public void openProductWithName(String nameOfProduct){
        clickOnElement(driver.findElement(By.xpath("//a[.='" + nameOfProduct.toUpperCase() + "']")));
    }
}
