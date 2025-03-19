package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.xpath("//input[@id='searchtext']");
    private By locationButton = By.xpath("//span[contains(@class,'bigLocationBtn')]");
    private By applyButton = By.xpath("//button[contains(@class,'btn-primary') and contains(text(),'Apply')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void handleLocationPopup() {
        try {
            System.out.println("Waiting for 'Get My Location' button...");
            WebElement locationBtn = wait.until(ExpectedConditions.elementToBeClickable(locationButton));
            locationBtn.click();
            System.out.println("Location button clicked!");

            WebElement applyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(applyButton));
            applyBtn.click();
            System.out.println("Apply button clicked!");

            wait.until(ExpectedConditions.invisibilityOf(applyBtn));
            System.out.println("Location popup handled successfully!");

        } catch (TimeoutException e) {
            System.out.println("Timeout: Location popup not found or already handled.");
        } catch (Exception e) {
            System.out.println("Error while handling location popup: " + e.getMessage());
        }
    }

    public void searchProduct(String productName) {
        handleLocationPopup();

        try {
            System.out.println("Waiting for search box...");
            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            search.clear();
            search.sendKeys(productName);
            System.out.println("Typing product name: " + productName);

            search.sendKeys(Keys.ENTER);
            System.out.println("ENTER key pressed!");

        } catch (TimeoutException e) {
            System.out.println("Timeout: Search box not found.");
        } catch (Exception e) {
            System.out.println("Error while searching product: " + e.getMessage());
        }
    }
}
