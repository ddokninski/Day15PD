package search;

import base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.header.HeaderPage;
import pages.product.ProductMiniaturePage;
import pages.product.ProductsGridPage;

import java.util.List;


public class SearchLabelTest extends TestBase {

    @Test
    @DisplayName("YAML")
    void checkSearchLabel() {

        String titleOfRandomProduct = new ProductsGridPage(driver).getTitleOfRandomProduct();

        new HeaderPage(driver)
                .typeInToSearchLabel(titleOfRandomProduct)
                .clickOnSearchButton();

        List<ProductMiniaturePage> products = new ProductsGridPage(driver).getProducts();

        if (products.size() == 1) {
            for (ProductMiniaturePage product : products) {
                Assertions.assertThat(titleOfRandomProduct).isEqualTo(product.getTitle());
            }
        } else {
            System.out.println("there is no such product");
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void checkDropDownListOfSearchResults() throws InterruptedException {
        List<String> searchResultList = at(HeaderPage.class)
                .typeInToSearchLabel("HUMMINGBIRD")
                .collectSearchResultFromDropDownListToStringList();

        boolean isCorrect = at(HeaderPage.class)
                .checkCorrectionOfSearchResult(searchResultList, "HUMMINGBIRD");

        Assertions.assertThat(isCorrect).isEqualTo(true);
    }
}
