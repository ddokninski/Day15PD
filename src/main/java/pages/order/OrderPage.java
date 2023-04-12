package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

import java.util.Random;

public class OrderPage extends BasePage {
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-link-action='different-invoice-address']")
    private WebElement billingAddressDiffersFromShippingAddress;
    @FindBy(css = "input[name='address1']")
    private WebElement addressInput;
    @FindBy(css = "input[name='city']")
    private WebElement cityInput;
    @FindBy(css = "input[name='postcode']")
    private WebElement postalCodeInput;
    @FindBy(css = "button[name='confirm-addresses']")
    private WebElement confirmAddressButton;
    @FindBy(css = "button[name='confirmDeliveryOption']")
    private WebElement confirmDeliveryOptionButton;
    @FindBy(css = "#payment-option-1")
    private WebElement paymentOptionByCheckRadioButton;
    @FindBy(css = "input[id='conditions_to_approve[terms-and-conditions]']")
    private WebElement termsOfServiceCheckBox;
    @FindBy(css = "select[class='form-control form-control-select']")
    private WebElement stateSelect;
    @FindBy(css = "select[name='id_country']")
    private WebElement countrySelect;
    @FindBy(css = "#invoice-addresses article[class='address-item'] .address-footer .delete-address")
    private WebElement addNewInvoiceAddressButton;
    @FindBy(css = "#payment-confirmation button")
    private WebElement placeOrderButton;

    public OrderPage addDifferentBillingAddress() {
        elementIssDisplayed(billingAddressDiffersFromShippingAddress);
        clickOnElement(billingAddressDiffersFromShippingAddress);
        return this;
    }

    public OrderPage checkPossibilityToFillNewAddress() {
        if (!elementIssDisplayed(addressInput)) {
            clickOnElement(addNewInvoiceAddressButton);
        }
        return this;
    }

    public OrderPage fillAddressInput(String address) {
        sendKeysAndClear(addressInput, address);
        return this;
    }

    public OrderPage fillCityInput(String cityName) {
        sendKeysAndClear(cityInput, cityName);
        return this;
    }

    public OrderPage fillPostalCodeInput(String postalCode) {
        sendKeysAndClear(postalCodeInput, postalCode);
        return this;
    }

    public OrderPage confirmAddress() {
        clickOnElement(confirmAddressButton);
        return this;
    }

    public OrderPage confirmDeliveryOptions() {
        clickOnElement(confirmDeliveryOptionButton);
        return this;
    }

    public OrderPage choosePaymentOptionByCheck() {
//        clickOnElement(paymentOptionByCheckRadioButton);
        paymentOptionByCheckRadioButton.click();
        return this;
    }

    public OrderPage acceptTermOfService() {
//        clickOnElement(termsOfServiceCheckBox);
        termsOfServiceCheckBox.click();
        return this;
    }

    public SummaryOrderPage placeOrder() {
        clickOnElement(placeOrderButton);
        return new SummaryOrderPage(driver);
    }


    public String selectState() {
        Random random = new Random();
        Select select = new Select(stateSelect);
        select.selectByIndex(random.nextInt(select.getOptions().size() - 1));
        String value = stateSelect.getAttribute("value");
        for (WebElement option : select.getOptions()) {
            if (option.getAttribute("value").equals(value)) {
                return option.getText();
            }
        }
        return null;
    }
}
