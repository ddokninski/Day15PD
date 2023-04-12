package pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    static Logger logger = LoggerFactory.getLogger(BasePage.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    public BasePage(WebDriver driver) {
        initDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement parent) {
        initDriver(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }

    private void initDriver(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("web_element_timeout"))));
        actions = new Actions(driver);
    }

    public WebElement getRandomElement(List<WebElement> elementsList) {
        return elementsList.get(new Random().nextInt(elementsList.size()));
    }

    public double getPrice(WebElement element) {
        return Double.parseDouble(element.getText().replace(System.getProperty("currency"), ""));
    }

    public void waitToBeClickAble(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeClickAbleList(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean isDisplayed1(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean elementIssDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnElement(WebElement element) {
        waitToBeClickAble(element);
        logger.info("Clicking on element " + element.getText());
        element.click();
    }

    public void sendKeysToElement(WebElement element, String msg) {
        logger.info("Typing to " + element.getText());
        element.sendKeys(msg);
    }

    public void sendKeysAndClear(WebElement element, String msg) {
        element.clear();

        if (!element.getText().isEmpty()) {
            element.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        }
        sendKeysToElement(element, msg);
    }

    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }
}
