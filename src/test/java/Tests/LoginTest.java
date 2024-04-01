package Tests;

import Pages.LoginPage;
import Tests.BaseTest;
import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$x;

public class LoginTest extends BaseTest {

    private final static String BASE_URL = "https://www.saucedemo.com/";
    private final static String USER_NAME = "standard_user";
    private final static String INCORRECT_USER_NAME = "user";
    private final static String USER_PASSWORD = "secret_sauce";
    private final static String INCORRECT_USER_PASSWORD = "password";

    @Test
    public void loginSuccessTest() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.setUsername(USER_NAME);
        loginPage.setPassword(USER_PASSWORD);
        loginPage.clickLogin();
        $x("//div[@class = \"app_logo\"]").should(Condition.text("Swag Labs"));
    }

    @Test
    public void loginWithEmptyFieldsTest() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.clickLogin();
        $x("//div[@class = \"error-message-container error\"]")
                .should(Condition.text("Epic sadface: Username is required"));
    }

    @Test
    public void loginWithEmptyPasswordTest() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.setUsername(USER_NAME);
        loginPage.clickLogin();
        $x("//div[@class = \"error-message-container error\"]")
                .should(Condition.text("Epic sadface: Password is required"));
    }

    @Test
    public void loginWithIncorrectCredentialsTest() {
        LoginPage loginPage = new LoginPage(BASE_URL);
        loginPage.setUsername(INCORRECT_USER_NAME);
        loginPage.setPassword(INCORRECT_USER_PASSWORD);
        loginPage.clickLogin();
        $x("//div[@class = \"error-message-container error\"]")
                .should(Condition.text("Epic sadface: Username and password do not match any user in this service"));
    }
}
