import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsPageTests extends BaseTest {
    private final static String BASE_URL = "https://www.saucedemo.com/";
    private final static String USER_NAME = "standard_user";
    private final static String USER_PASSWORD = "secret_sauce";

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.setUsername(USER_NAME);
        loginPage.setPassword(USER_PASSWORD);
        loginPage.clickLogin();
    }

    @Test
    public void addToCartBadgeTest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.addToCartFirstProduct();
        Assert.assertTrue(productsPage.getShoppingCartBadge().isDisplayed());
    }

    @Test
    public void firstProductHasPriceTest() {
        ProductsPage productsPage = new ProductsPage();
        Assert.assertTrue(productsPage.getFirstProductPrice().should().exists());
    }

    @Test
    public void filterByNameFromAtoZTest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickOnFilterButton().setFilterAZ();
        Assert.assertTrue(productsPage.areStringsSortedAlphabetically(productsPage.getFirstProductDescription(),
                productsPage.getLastProductDescription()));
    }

    @Test
    public void filterByNameFromZtoATest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickOnFilterButton().setFilterZA();
        Assert.assertTrue(productsPage.areStringsSortedAlphabetically(productsPage.getLastProductDescription(),
                productsPage.getFirstProductDescription()));
    }

    @Test
    public void filterByPriceFromLowToHigh() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickOnFilterButton().setFilterLowHigh();
        Assert.assertTrue(productsPage.isFirstPriceLessThanSecond(productsPage.getFirstProductPrice().text(),
                productsPage.getLastProductPrice().text()));
    }

    @Test
    public void filterByPriceFromHighToLow() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickOnFilterButton().setFilterHighLow();
        Assert.assertTrue(productsPage.isFirstPriceLessThanSecond( productsPage.getLastProductPrice().text(),
                productsPage.getFirstProductPrice().text()));
    }

    @Test
    public void addProductToCartTest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.addToCartFirstProduct();
        String productNameAdded = productsPage.getFirstProductDescription();
        String productNameInShoppingCart = productsPage.openShoppingCart().getAddedToCartFirstProductDescription();
        Assert.assertEquals(productNameAdded, productNameInShoppingCart);
    }
}
