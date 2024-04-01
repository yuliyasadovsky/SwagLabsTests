package Tests;

import Pages.LoginPage;
import Pages.ProductsPage;
import Tests.BaseTest;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertTrue(productsPage.areStringsSortedAlphabetically(productsPage.getFirstProductName(),
                productsPage.getLastProductName()));
    }

    @Test
    public void filterByNameFromZtoATest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickOnFilterButton().setFilterZA();
        Assert.assertTrue(productsPage.areStringsSortedAlphabetically(productsPage.getLastProductName(),
                productsPage.getFirstProductName()));
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
        String productNameAdded = productsPage.getFirstProductName();
        String productNameInShoppingCart = productsPage.openShoppingCart().getFirstProductName();
        Assert.assertEquals(productNameAdded, productNameInShoppingCart);
    }

    @Test
    public void openAllHrefsTest() {
        ProductsPage productsPage = new ProductsPage();
        List<String> links = productsPage.getProductsLinks();
        for (int i = 0; i < links.size(); i++) {
            String pageUrl = links.get(i);
            Selenide.open(pageUrl);
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            Assert.assertEquals(pageUrl, currentUrl);
        }
    }

    @Test
    public void useragent() {
        System.out.println(Selenide.getUserAgent());
    }
}
