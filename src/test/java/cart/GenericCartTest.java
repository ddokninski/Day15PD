package cart;

import base.TestBase;
import configuration.config.UrlProvider;
import models.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.product.ProductDetailsPage;
import pages.product.ProductsGridPage;

import java.util.Random;

public class GenericCartTest extends TestBase {

    @Test
    void verifyCartCalculations() {
        Order expectedOrder = new Order();

        for (int i = 0; i < 10; i++) {
            driver.get(UrlProvider.baseUrl);
            at(ProductsGridPage.class)
                    .openRandomProduct();

            at(ProductDetailsPage.class)
                    .setQuantity(new Random().nextInt(5) + 1);

            expectedOrder.addOrderLine(at(ProductDetailsPage.class)
                    .getOrderLineDetails());

            at(ProductDetailsPage.class)
                    .addToCart();
        }

        driver.get(UrlProvider.cartUrl);

        CartPage cartPage = new CartPage(driver);
        Assertions.assertThat(cartPage.toOrder())
                .usingRecursiveComparison()
                .isEqualTo(expectedOrder);
    }


}
