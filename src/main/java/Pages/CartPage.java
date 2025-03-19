package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartButton = By.xpath("//div[@role='button' and @data-bs-toggle='offcanvas'][@href='#offcanvasCart']");
    private By checkoutButton = By.xpath("//a[contains(@class, 'btn btn-primary') and contains(text(), 'Checkout')]");
    private By productNameLocator = By.xpath("//a[contains(@class, 'cartProductName')]");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Open the cart
    public void openCart() {
        try {
            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
            cart.click();
            System.out.println("Cart opened successfully.");
        } catch (TimeoutException e) {
            System.out.println("Cart button not found or not clickable.");
        }
    }

    public String getCartProductName() {
        try {
            WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));
            String productName = productNameElement.getText().trim();
            System.out.println("Found product in cart: " + productName);
            return productName;
        } catch (TimeoutException e) {
            System.out.println("Product name not found in cart.");
            return "";
        }
    }

    public void clickCheckoutButton() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            checkout.click();
            System.out.println("Checkout button clicked.");
        } catch (TimeoutException e) {
            System.out.println("Checkout button not found or not clickable.");
        }
    }
}


