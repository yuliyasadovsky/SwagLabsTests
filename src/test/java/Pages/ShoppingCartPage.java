package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartPage {
    private final ElementsCollection addedToCartProductsList = $$x("//div[@class = \"inventory_item_name\"]");
    private final ElementsCollection addedToCartProductsDescriptions = $$x("//div[@class = \"inventory_item_desc\"]");

    public static String NAME = "name";
    public static String DESCRIPTION = "description";

    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put(NAME, getFirstProductName());
        attributes.put(DESCRIPTION, getFirstProductDescription());
        return attributes;
    }
    public String  getFirstProductName() {
        return addedToCartProductsList.first().text();
    }
    public String  getFirstProductDescription() {
        return addedToCartProductsDescriptions.first().text();
    }
}
