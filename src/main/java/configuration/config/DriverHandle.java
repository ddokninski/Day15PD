package configuration.config;

import configuration.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class DriverHandle {

    private WebDriver driver;

    public WebDriver getDriver() {
        switch (Browser.valueOf(System.getProperty("browser_name").toUpperCase())) {
            case CHROME -> {
                getChromeDriver();
            }
            case FIREFOX -> {
                getFirefoxDriver();
            }
            case EDGE -> {
                getEdgeDriver();
            }
            case IE -> {
                getIeDriver();
            }
        }
        return driver;
    }

    private void getChromeDriver() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        driver = new ChromeDriver(optionsChrome);
    }

    private void getFirefoxDriver() {
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("start-maximized");
        driver = new FirefoxDriver(optionsFirefox);
    }

    private void getEdgeDriver() {
        EdgeOptions optionsEdge = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        optionsEdge.addArguments("start-maximized");
        driver = new EdgeDriver(optionsEdge);
    }

    private void getIeDriver() {
        InternetExplorerOptions optionsIe = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        optionsIe.addCommandSwitches("start-maximized");
        driver = new InternetExplorerDriver(optionsIe);
    }
}
