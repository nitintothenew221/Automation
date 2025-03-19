package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productImages = By.xpath("//a[text()='Amul Kool Protein Milkshake | Chocolate, 180 mL | Pack of 30']");
    private By addToCartButtons = By.xpath("//a[@title='Add to Cart']");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click on the first available product
    public void clickOnProduct() {
        try {
            List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productImages));
            if (!products.isEmpty()) {
                products.get(0).click(); // Clicks the first product
            } else {
                System.out.println("No products found.");
            }
        } catch (TimeoutException e) {
            System.out.println("Product not found.");
        }
    }

    public void clickAddToCart() {
        try {
            List<WebElement> cartButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addToCartButtons));
            if (!cartButtons.isEmpty()) {
                cartButtons.get(0).click();
            } else {
                System.out.println("Add to Cart button not found.");
            }
        } catch (TimeoutException e) {
            System.out.println("Unable to add to cart.");
        }
    }
}
