package pages.header;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class HeaderSubCategoryPage {

    @FindBy(xpath = "#top-menu>li:nth-of-type(1) a[class='dropdown-item dropdown-submenu']")
    private List<WebElement> clothesSubCategoriesList;

    public HeaderSubCategoryPage(WebElement element){
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public List<WebElement> getClothesSubCategoriesList() {
        return  clothesSubCategoriesList;
    }

}
