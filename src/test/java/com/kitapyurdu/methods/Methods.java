package com.kitapyurdu.methods;

import com.kitapyurdu.driver.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Methods {
    WebDriver driver;
    JavascriptExecutor jsdriver;
    FluentWait<WebDriver> wait;

    Logger logger = LogManager.getLogger(Methods.class);

    public Methods() {
        driver = BaseTest.driver;
        jsdriver = (JavascriptExecutor) driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30)).
                pollingEvery(Duration.ofMillis(300)).
                ignoring(NoSuchElementException.class);
    }

    public void LogInsert(String text) {
        System.out.println(text);
        logger.info(text);
    }

    public WebElement FindElement(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public List<WebElement> FindElements(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        return driver.findElements(element);
    }

    public void WaitUntilClickable(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void Click(By element) {
        if (element != null) {
            FindElement(element).click();
        } else {
            LogInsert("Parameter `element` is null");
        }
    }

    public void SendKeys(By element, String text) {
        if (element != null && text != null) {
            FindElement(element).sendKeys(text);
        } else {
            LogInsert("One of parameter is null");
        }
    }

    public void ClearInput(By element) {
        FindElement(element).clear();
    }

    public boolean IsElementVisible(By element) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (Exception e) {
            LogInsert(element + " is not visible " + e.getMessage());
            return false;
        }
    }

    public void Scroll(By element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(FindElement(element)).build().perform();
        } catch (Exception e) {
            LogInsert("Failed " + e.getMessage());
        }
    }

    public void ClickWithJavascript(By element) {
        try {
            jsdriver.executeScript("arguments[0].click()", FindElement(element));
        } catch (Exception e) {
            LogInsert("Failed to click with javascript: " + e.getMessage());
        }
    }

    public Select GetSelect(By element) {
        try {
            return new Select(FindElement(element));
        } catch (Exception e) {
            LogInsert("Failed " + e.getMessage());
            return null;
        }
    }

    public void SelectByText(By element, String text) {
        try {
            GetSelect(element).selectByVisibleText(text);
        } catch (Exception e) {
            LogInsert("Error: " + e.getMessage());
        }
    }

    public void SelectByValue(By element, String text) {
        GetSelect(element).selectByValue(text);
    }

    public String GetSelectedOptionText(By element) {
        try {
            return new Select(FindElement(element)).getFirstSelectedOption().getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String GetAttribute(By element, String attributeName) {
        try {
            return FindElement(element).getAttribute(attributeName);
        } catch (Exception e) {
            LogInsert("Error: " + e.getMessage());
            return null;
        }
    }

    public String GetText(By element) {
        try {
            return FindElement(element).getText();
        } catch (Exception e) {
            LogInsert("Error: " + e.getMessage());
            return null;
        }
    }

    public String GetValue(By element) {
        try {
            return jsdriver.executeScript("return arguments[0].value;", FindElement(element)).toString();
        } catch (Exception e) {
            LogInsert("Error: " + e.getMessage());
            return null;
        }
    }

    public String IsItemAlreadyFavorited(By item) {
        try {
            String result = GetAttribute(item, "style");
            if (result != null && result.equals("display: none;")) {
                return "false";
            } else {
                return "true";
            }

        } catch (Exception e) {
            System.out.println("Error: " + Thread.currentThread().getStackTrace()[1]);
            return "error";
        }
    }

    public void ScrollWithAction(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public Integer randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max-min) + min;
    }

}
