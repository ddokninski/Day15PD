package cart;

import base.TestBase;
import configuration.config.UrlProvider;
import models.OrderLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.product.ProductDetailsPage;
import pages.product.ProductsGridPage;

import java.util.List;

public class RemoveProduct extends TestBase {

    @Test
    void verifyRemovingProductFromCart() throws InterruptedException {

        for (int i = 0; i < 2; i++) {
            driver.get(UrlProvider.baseUrl);
            at(ProductsGridPage.class)
                    .openRandomProduct();

            at(ProductDetailsPage.class)
                    .addToCart();
        }

        driver.get(UrlProvider.cartUrl);

        double expectedValue = getExpectedValue();
        double actualTotalProductPrice = at(CartPage.class).getTotalProductsPrice();

        Assertions.assertThat(expectedValue).isEqualTo(actualTotalProductPrice);

        actualTotalProductPrice = at(CartPage.class)
                .removeFirstProductFromCart()
                .getTotalProductsPrice();

        expectedValue = getExpectedValue();

        Assertions.assertThat(expectedValue).isEqualTo(actualTotalProductPrice);

        at(CartPage.class)
                .removeFirstProductFromCart();
        String textFromEmptyBasket = at(CartPage.class).getTextFromEmptyBasket();

        Assertions.assertThat(textFromEmptyBasket).isEqualTo("There are no more items in your cart");
    }

    private double getExpectedValue() {
        List<OrderLine> orderLines = at(CartPage.class).toOrder().getOrderLines();
        double expectedValue = 0;
        for (OrderLine orderLine : orderLines) {
            expectedValue += orderLine.getTotalPrice();
        }
        return expectedValue;
    }
}
