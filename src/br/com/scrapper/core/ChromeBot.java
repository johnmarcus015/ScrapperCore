package br.com.scrapper.core;

import br.com.scrapper.utils.DriverSelector;
import br.com.scrapper.utils.OSDetector;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeBot {

    public static WebDriver driver;

    public static void init(boolean showUI) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", new DriverSelector().selectDriverToPlatform());

            if (showUI) {
                driver = new ChromeDriver();
            } else {
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(!showUI);
                driver = new ChromeDriver(options);
            }

            driver.manage().window().maximize();
        }
    }

    public static void goTo(String url) {
        //if(url != driver.getCurrentUrl()){
        newTab();
        goToLastTab();
        //}
        driver.get(url);
    }

    public static void wait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void waitComponent(int seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement getElementByXPath(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public static WebElement getElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public static WebElement getElementByClass(String className) {
        return driver.findElement(By.className(className));
    }

    public static void newTab() {
        if (!driver.getCurrentUrl().equals("data:,")) {

            String os = DriverSelector.selectDriverToPlatform();
            boolean isWindows = os.equals(OSDetector.OSType.Windows.name());
            boolean isLinux = os.equals(OSDetector.OSType.Linux.name());
            boolean isMac = os.equals(OSDetector.OSType.MacOS.name());

            if (isMac) {
                ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
            } else if (isLinux || isWindows) {
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
            }
        }
    }

    public static void goToLastTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static void goToFirstTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }
}
