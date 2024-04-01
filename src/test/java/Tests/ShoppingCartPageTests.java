package Tests;

import Pages.ProductsPage;
import Pages.ShoppingCartPage;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartPageTests extends ProductsPageTests {

    @Test
    public void productDescriptionTest() {
        Map<String, String> expected = new HashMap<>();
        expected.put(ShoppingCartPage.NAME, "Sauce Labs Backpack");
        expected.put(ShoppingCartPage.DESCRIPTION, "carry.allTheThings() with the sleek, " +
                "streamlined Sly Pack that melds uncompromising " +
                "style with unequaled laptop and tablet protection.");
        ProductsPage productsPage = new ProductsPage();
        productsPage.addToCartFirstProduct();
        Map<String, String> actual = productsPage.openShoppingCart().getAttributes();
        Assert.assertEquals(expected, actual);
    }
}
