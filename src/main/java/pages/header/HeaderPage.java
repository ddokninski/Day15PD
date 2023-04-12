package pages.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.sign_in.SignInPage;

import java.util.ArrayList;
import java.util.List;

public class HeaderPage extends BasePage {
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".search-widget .ui-autocomplete-input")
    private WebElement searchWidget;

    @FindBy(css = "button i[class='material-icons search']")
    private WebElement searchButton;

    @FindBy(css = "a[class='ui-corner-all']")
    private List<WebElement> searchResults;

    @FindBy(css = "#top-menu>li")
    private List<WebElement> mainCategories;

    @FindBy(css = "#top-menu>li:nth-of-type(2)")
    private WebElement accesoriesCategory;
    @FindBy(css = "#top-menu>li:nth-of-type(3)")
    private WebElement artCategory;
    @FindBy(css = ".cart-products-count")
    private WebElement cartProductCounter;
    @FindBy(css = "a[title='Log in to your customer account']")
    private WebElement signInButton;

    @FindBy(css = ".account")
    private WebElement myAccountButton;


    public HeaderPage typeInToSearchLabel(String text) {
        sendKeysAndClear(searchWidget, text);
        return this;
    }

    public HeaderPage clickOnSearchButton() {
        clickOnElement(searchButton);
        return this;
    }

    public List<String> collectSearchResultFromDropDownListToStringList() {
        waitToBeClickAbleList(searchResults);
        List<String> stringList = new ArrayList<>();
        for (WebElement searchResult : searchResults) {
            WebElement element = searchResult.findElement(By.xpath("./span[3]"));
            stringList.add(element.getText());
        }
        return stringList;
    }

    public boolean checkCorrectionOfSearchResult(List<String> resultList, String searchedText) {

        for (String s : resultList) {
            if (!s.contains(searchedText)) {
                return false;
            }
        }
        return true;
    }

    public List<WebElement> getCategoryList() {
        return mainCategories;
    }

    public void clickOnCategory(int i) {
        clickOnElement(mainCategories.get(i));
    }

    public void goToAccessoriesCategory() {
        clickOnElement(accesoriesCategory);
    }

    public void goToArtCategory() {
        clickOnElement(artCategory);
    }

    public void clickOnSubCategory(int numberOfMainCategory, int numberOfSubcategory) throws InterruptedException {
        List<WebElement> subCategory = mainCategories.get(numberOfMainCategory).findElements(By.cssSelector("a[class='dropdown-item dropdown-submenu']"));
        clickOnElement(subCategory.get(numberOfSubcategory));
    }

    public void getSubCategories() {
        List<HeaderSubCategoryPage> subCategories = new ArrayList<>();

    }

    public List<WebElement> quantityOfSubCategories(int i) {
        return mainCategories.get(i).findElements(By.cssSelector("a[class='dropdown-item dropdown-submenu']"));
    }

    public HeaderPage hoverOverOnElement(int i) throws InterruptedException {
        hoverOverElement(mainCategories.get(i));
        return this;
    }

    public String getSubcategoryName(int numberOfMainCategory, int numberOfSubcategory) {
        List<WebElement> subCategory = mainCategories.get(numberOfMainCategory).findElements(By.cssSelector("a[class='dropdown-item dropdown-submenu']"));
        return subCategory.get(numberOfSubcategory).getText();
    }

    public int getQuantityOfProductsInCart() {
        return Integer.parseInt(cartProductCounter.getText().replace("(", "").replace(")", ""));
    }

    public SignInPage clickOnSignInButton() {
        clickOnElement(signInButton);
        return new SignInPage(driver);
    }

    public void goToMyAccount() {
        clickOnElement(myAccountButton);
    }

}
