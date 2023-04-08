package filters;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.filter.FilterPage;
import pages.header.HeaderPage;
import pages.product.ProductMiniaturePage;
import pages.product.ProductsGridPage;

@Slf4j
public class FiltersTest extends TestBase {

    @Test
    void checkFilteringByPrice() throws InterruptedException {
        int minPrice = 13;
        int maxPrice = 15;
        at(HeaderPage.class).goToAccessoriesCategory();

        int quantityOfProductsBeforeFilter = at(ProductsGridPage.class)
                .getProducts().size();

        at(FilterPage.class)
                .setPriceRange(minPrice, maxPrice);

        for (ProductMiniaturePage product : at(ProductsGridPage.class)
                .getProducts()) {
            if (minPrice > product.getPrice() || maxPrice < product.getPrice()) {
                log.error("Product with out of range price is visible");
                Assertions.assertThat(true).isEqualTo(false);
            }
        }

        at(FilterPage.class)
                .clearAllFilters();

        Assertions.assertThat(at(ProductsGridPage.class).getProducts().size()).isEqualTo(quantityOfProductsBeforeFilter);


    }


}
