import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public LoginPage(String url) {
        Selenide.open(url);
    }
    private final SelenideElement usernameField = $x("//input[@placeholder = \"Username\"]");
    private final SelenideElement passwordField = $x("//input[@placeholder = \"Password\"]");

    private final SelenideElement loginButton = $x("//input[@name = \"login-button\"]");

    public void setUsername(String username) {
        usernameField.click();
        usernameField.setValue(username);
    }

    public void setPassword(String password) {
        passwordField.click();
        passwordField.setValue(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}
