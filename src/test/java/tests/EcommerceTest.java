package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.CartPage;
import java.time.Duration;

public class EcommerceTest {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://shop.amul.com/en/");

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testSearchAddToCartAndCheckout() {
        try {
            String expectedProductName = "Amul Kool Thandai, 180 mL | Pack of 30";

            homePage.searchProduct(expectedProductName);
            productPage.clickOnProduct();
            productPage.clickAddToCart();
            cartPage.openCart();
            cartPage.clickCheckoutButton();

            String actualProductName = cartPage.getCartProductName();
            Assert.assertFalse(actualProductName.isEmpty(), "Product name is empty in cart!");
            Assert.assertEquals(actualProductName, expectedProductName, "Cart product name does NOT match!");
        } catch (Exception e) {
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


