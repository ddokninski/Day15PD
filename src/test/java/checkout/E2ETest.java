package checkout;

import base.TestBase;
import com.github.javafaker.Faker;
import configuration.config.UrlProvider;
import models.Address;
import models.OrderHistoryLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.cart.PopUpCart;
import pages.header.HeaderPage;
import pages.my_Account.MyAccount;
import pages.my_Account.OrderHistoryTable;
import pages.my_Account.OrderRowPage;
import pages.order.OrderDetailsPage;
import pages.order.OrderPage;
import pages.order.SummaryOrderPage;
import pages.product.ProductDetailsPage;
import pages.product.ProductsGridPage;
import pages.sign_in.SignInPage;

import java.util.List;

public class E2ETest extends TestBase {

    @Test
    void verifyEndToEndToEndBuyingStore() {
        Faker faker = new Faker();

        String productName = "THE BEST IS YET POSTER";

        at(HeaderPage.class).clickOnSignInButton();

        at(SignInPage.class).loginUser(System.getProperty("user_email"), System.getProperty("user_password"));

        driver.get(UrlProvider.baseUrl);

        at(HeaderPage.class).goToArtCategory();

        at(ProductsGridPage.class).openProductWithName(productName);

        at(ProductDetailsPage.class).addToCart();

        at(PopUpCart.class).proceedToCheckoutAction();

        at(CartPage.class).clickOnProceedToCheckoutButton();

        String selectedState = at(OrderPage.class).addDifferentBillingAddress()
                .checkPossibilityToFillNewAddress()
                .selectState();

        Address address = new Address(faker.address().streetAddress(), faker.address().cityName(), faker.numerify("#####"), selectedState, System.getProperty("user_country"));

        at(OrderPage.class)
                .fillAddressInput(address.getStreetName())
                .fillCityInput(address.getCityName());


        at(OrderPage.class)
                .fillPostalCodeInput(address.getPostalCode())
                .confirmAddress()
                .confirmDeliveryOptions()
                .choosePaymentOptionByCheck()
                .acceptTermOfService()
                .placeOrder();

        String orderNumber = at(SummaryOrderPage.class).getOrderNumber();

        at(HeaderPage.class).goToMyAccount();

        at(MyAccount.class).goToOrderHistory();

        List<OrderRowPage> orders = at(OrderHistoryTable.class).getOrders();

        OrderHistoryLine orderHistoryLine = null;
        for (OrderRowPage order : orders) {
            if (order.getOrderNumber().equals(orderNumber)) {
                orderHistoryLine = order.toOrderHistoryLine(order);
                order.goToOrderDetails();
                break;
            }
        }

        Assertions.assertThat(at(OrderDetailsPage.class).getStatusOfPayment()).isEqualTo(orderHistoryLine.getStatusOfPayment());
        Assertions.assertThat(at(OrderDetailsPage.class).getDateOfOrder()).isEqualTo(orderHistoryLine.getDate());
        Assertions.assertThat(at(OrderDetailsPage.class).getTotalPrice()).isEqualTo(orderHistoryLine.getTotalPrice());


        String deliveryAddress = System.getProperty("user_first_name") + " " + System.getProperty("user_last_name") + "\n"
                + System.getProperty("user_street") + "\n"
                + System.getProperty("user_city") + ", " + System.getProperty("user_post_code") + "\n"
                + System.getProperty("user_country");


        Assertions.assertThat(at(OrderDetailsPage.class).getDeliveryAddress()).isEqualTo(deliveryAddress);
        Assertions.assertThat(at(OrderDetailsPage.class).getInvoiceAddress()).contains(address.getCountry());
        Assertions.assertThat(at(OrderDetailsPage.class).getInvoiceAddress()).contains(address.getState());
        Assertions.assertThat(at(OrderDetailsPage.class).getInvoiceAddress()).contains(address.getCityName());
        Assertions.assertThat(at(OrderDetailsPage.class).getInvoiceAddress()).contains(address.getStreetName());
        Assertions.assertThat(at(OrderDetailsPage.class).getInvoiceAddress()).contains(address.getPostalCode());

    }
}
