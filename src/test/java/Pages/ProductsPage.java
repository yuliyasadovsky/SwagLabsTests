package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class ProductsPage {
    private final ElementsCollection productsList = $$x("//div[@class = \"inventory_item_name \"]");
    private final ElementsCollection addToCartButtons = $$x("//button[@class = \"btn btn_primary btn_small btn_inventory \"]");
    private final SelenideElement shoppingCartBadge = $x("//span[@class = \"shopping_cart_badge\"]");
    private final ElementsCollection priceLabels = $$x("//div[@class = \"inventory_item_price\"]");
    private final SelenideElement filterButton = $x("//select");
    private final SelenideElement filterOptionAZ = $x("//select//option[@value = \"az\"]");
    private final SelenideElement filterOptionZA = $x("//select//option[@value = \"za\"]");
    private final SelenideElement filterOptionLowHigh = $x("//select//option[@value = \"lohi\"]");
    private final SelenideElement filterOptionHighLow = $x("//select//option[@value = \"hilo\"]");
    private final SelenideElement shoppingCartButton = $x("//div//a[@class = \"shopping_cart_link\"]");
    private final ElementsCollection productsLinks = $$x("//div[@class='inventory_item_label']//a[@href]");

    public String getFirstProductName() {
        return productsList.first().text();
    }

    public void openProductsLinks() {
        List<String> links = new ArrayList<>();
        productsLinks.forEach(x->links.add(x.getAttribute("href")));
        links.forEach(Selenide::open);
    }

    public List<String> getProductsLinks() {
        List<String> links = new ArrayList<>();
        productsLinks.forEach(x->links.add(x.getAttribute("href")));
        return links;
    }

    public String  getLastProductName() {
        return productsList.last().text();
    }
    public SelenideElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }

    public SelenideElement getFirstProductPrice() {
        return priceLabels.first();
    }

    public SelenideElement getLastProductPrice() {
        return priceLabels.last();
    }
    public void addToCartFirstProduct() {
        addToCartButtons.first().click();
    }

    public ShoppingCartPage openShoppingCart() {
        shoppingCartButton.click();
        return new ShoppingCartPage();
    }

    public ProductsPage clickOnFilterButton() {
        filterButton.click();
        return new ProductsPage();
    }

    public void setFilterAZ() {
        filterOptionAZ.click();
    }

    public void setFilterZA() {
        filterOptionZA.click();
    }

    public void setFilterLowHigh() {
        filterOptionLowHigh.click();
    }

    public void setFilterHighLow() {
        filterOptionHighLow.click();
    }

    public boolean areStringsSortedAlphabetically(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < minLength; i++) {
            char char1 = Character.toLowerCase(str1.charAt(i));
            char char2 = Character.toLowerCase(str2.charAt(i));

            if (char1 != char2) {
                return char1 < char2;
            }
        }

        return str1.length() <= str2.length();
    }

    public boolean isFirstPriceLessThanSecond(String price1, String price2) {
        double num1 = Double.parseDouble(price1.substring(1));
        double num2 = Double.parseDouble(price2.substring(1));
        return num1 < num2;
    }
}
