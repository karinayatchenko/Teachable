package teacheableLoginTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    public WebDriver driver;

    protected boolean isElementVisible(By by, int timeOutInSeconds) {
        try {
            this.findElement(by, timeOutInSeconds);
            return true;
        } catch (WebDriverException var4) {
            return false;
        }
    }

    protected WebElement findElement(By by, int timeOutInSeconds) {
        return (WebElement) (new WebDriverWait(this.driver, (long) timeOutInSeconds, 30L)).withMessage(String.format("Timed out waiting for: %s", by.toString())).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
