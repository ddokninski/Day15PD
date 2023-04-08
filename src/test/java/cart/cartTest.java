package cart;

import base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.cart.PopUpCart;
import pages.header.HeaderPage;
import pages.product.ProductDetailsPage;
import pages.product.ProductsGridPage;

public class cartTest extends TestBase {

    @Test
    void verifyAddingProductToCart() {
        int quantity = 3;
        String productName = "THE BEST IS YET POSTER";

        at(HeaderPage.class)
                .goToArtCategory();

        at(ProductsGridPage.class)
                .openProductWithName(productName);

        double productPrice = at(ProductDetailsPage.class)
                .setQuantity(quantity)
                .getProductPrice();

        at(ProductDetailsPage.class)
                .addToCart();

        Assertions.assertThat(at(PopUpCart.class).getProductName()).isEqualTo(productName);
        Assertions.assertThat(at(PopUpCart.class).getProductPrice()).isEqualTo(productPrice);
        Assertions.assertThat(at(PopUpCart.class).getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(at(PopUpCart.class).getTotalPrice()).isEqualTo(productPrice * quantity);

        at(PopUpCart.class)
                .continueShopping();

        Assertions.assertThat(at(HeaderPage.class).getQuantityOfProductsInCart()).isEqualTo(quantity);
    }

}
