import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartPage {
    private final ElementsCollection addedToCartProductsList = $$x("//div[@class = \"inventory_item_name\"]");

    public String  getAddedToCartFirstProductDescription() {
        return addedToCartProductsList.first().text();
    }
}
