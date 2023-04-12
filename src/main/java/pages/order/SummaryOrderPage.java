package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.Arrays;
import java.util.List;

public class SummaryOrderPage extends BasePage {
    public SummaryOrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-details ul li:nth-of-type(1)")
    private WebElement orderReference;

    public String getOrderNumber() {
        String orderNumber = orderReference.getText();

        List<String> strings = Arrays.stream((orderNumber.split(" "))).toList();

        return strings.get(strings.size() - 1);
    }
}
