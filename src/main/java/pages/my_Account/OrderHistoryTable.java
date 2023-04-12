package pages.my_Account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryTable extends BasePage {

    public OrderHistoryTable(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> rows;

    public List<OrderRowPage> getOrders() {
        List<OrderRowPage> orders = new ArrayList<>();

        for (WebElement order : rows) {
            orders.add(new OrderRowPage(driver, order));
        }
        return orders;
    }
}
