package categories;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.category_block.CategoryBlock;
import pages.filter.FilterPage;
import pages.header.HeaderPage;
import pages.product.ProductMiniaturePage;
import pages.product.ProductsGridPage;

import java.util.List;

@Slf4j
public class CategoriesTest extends TestBase {

    @Test
    void iterateTroughEachCategoryAndCheckSites() {
        List<WebElement> categoryList = at(HeaderPage.class)
                .getCategoryList();

        for (int i = 0; i < categoryList.size(); i++) {
            at(HeaderPage.class).clickOnCategory(i);
            String expectedCategoryName = categoryList.get(i).getText();

            String actualCategoryName = at(CategoryBlock.class)
                    .getCategoryName();

            boolean filterIsVisible = at(FilterPage.class)
                    .checkVisibilityOfSearchFiltersWrapper();

            List<ProductMiniaturePage> products = at(ProductsGridPage.class)
                    .getProducts();

            String infoAboutQuantityOfProductsOnPage = at(CategoryBlock.class)
                    .infoAboutQuantityOfProductsOnPage();

            boolean correctProductsQuantity = infoAboutQuantityOfProductsOnPage.contains(String.valueOf(products.size()));


            Assertions.assertThat(correctProductsQuantity).isEqualTo(true);
            Assertions.assertThat(actualCategoryName).isEqualTo(expectedCategoryName);
            Assertions.assertThat(filterIsVisible).isEqualTo(true);

        }
    }


    @Test
    void iterateTroughEachSubCategoryAndCheckSites() throws InterruptedException {
        List<WebElement> categoryList = at(HeaderPage.class)
                .getCategoryList();

        for (int i = 0; i < categoryList.size() - 1; i++) {
            List<WebElement> subCategoriesList = at(HeaderPage.class)
                    .quantityOfSubCategories(i);
            for (int j = 0; j < subCategoriesList.size(); j++) {
                String expectedCategoryName = at(HeaderPage.class)
                        .hoverOverOnElement(i)
                        .getSubcategoryName(i, j);

                at(HeaderPage.class)
                        .clickOnSubCategory(i, j);

                String actualCategoryName = at(CategoryBlock.class)
                        .getCategoryName();

                boolean filterIsVisible = at(FilterPage.class)
                        .checkVisibilityOfSearchFiltersWrapper();

                List<ProductMiniaturePage> products = at(ProductsGridPage.class)
                        .getProducts();

                String infoAboutQuantityOfProductsOnPage = at(CategoryBlock.class)
                        .infoAboutQuantityOfProductsOnPage();

                boolean correctProductsQuantity = infoAboutQuantityOfProductsOnPage.contains(String.valueOf(products.size()));


                Assertions.assertThat(correctProductsQuantity).isEqualTo(true);
                Assertions.assertThat(actualCategoryName).isEqualTo(expectedCategoryName);
                Assertions.assertThat(filterIsVisible).isEqualTo(true);
            }

        }
    }
}
