package pages.filter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class FilterPage extends BasePage {
    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search_filters_wrapper")
    private WebElement searchFiltersWrapper;

    @FindBy(css = "a[class='ui-slider-handle ui-state-default ui-corner-all']:nth-of-type(1)")
    private WebElement lowestPriceSlider;
    @FindBy(css = "a[class='ui-slider-handle ui-state-default ui-corner-all']:nth-of-type(2)")
    private WebElement highestPriceSlider;
    @FindBy(css = ".faceted-slider")
    private WebElement priceRange;
    @FindBy(css = ".js-search-filters-clear-all")
    private WebElement clearFiltersButton;
    @FindBy(css = ".spinner")
    private WebElement loaderAnimation;

    public boolean checkVisibilityOfSearchFiltersWrapper() {

        return elementIssDisplayed(searchFiltersWrapper);
    }

    public FilterPage setPriceRange(int minPrice, int maxPrice) {
        double actualMinPrice = getActualMinPrice();
        double actualMaxPrice = getActualMaxPrice();

        while (true) {
            waitToBeClickAble(lowestPriceSlider);
            if (minPrice > actualMinPrice) {
                actions
                        .clickAndHold(lowestPriceSlider)
                        .sendKeys(Keys.RIGHT)
                        .perform();
                actualMinPrice = getActualMinPrice();
            } else if (minPrice < actualMinPrice) {
                actions
                        .clickAndHold(lowestPriceSlider)
                        .sendKeys(Keys.LEFT)
                        .perform();
            } else {
                break;
            }
        }

        while (true) {
            waitToBeClickAble(highestPriceSlider);
            if (maxPrice < actualMaxPrice) {
                actions
                        .clickAndHold(highestPriceSlider)
                        .sendKeys(Keys.LEFT)
                        .perform();
                actualMaxPrice = getActualMaxPrice();
            } else if (maxPrice > actualMaxPrice) {
                actions
                        .clickAndHold(highestPriceSlider)
                        .sendKeys(Keys.RIGHT)
                        .perform();
            } else {
                break;
            }
        }
        return this;
    }

    public double getActualMinPrice() {
        String actualPriceRange = priceRange.getText().replace(System.getProperty("currency"), "");
        String[] actualPriceTable = actualPriceRange.split("-");
        return Double.parseDouble(actualPriceTable[0].trim());
    }

    public double getActualMaxPrice() {
        String actualPriceRange = priceRange.getText().replace(System.getProperty("currency"), "");
        String[] actualPriceTable = actualPriceRange.split("-");
        return Double.parseDouble(actualPriceTable[1].trim());
    }

    public FilterPage clearAllFilters() throws InterruptedException {
        clickOnElement(clearFiltersButton);
        Thread.sleep(1000);
        return this;
    }

}
