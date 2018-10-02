package com.E2E;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.Keys;
import qa.pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullCheckoutTest extends FrameworkTest {

    UserLoginPage userLoginPage;

    @BeforeEach
    public void initPage() {
        userLoginPage = new UserLoginPage(getDriver());
        userLoginPage.open();
        userLoginPage.getEmailInput().sendKeys("mytestathon@megamail.com");
        userLoginPage.getPasswordInput().sendKeys("qwas");
        userLoginPage.getLoginButton().click();
    }

    @DisplayName("Checkout full cycle")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void fullCheckoutProcess() {
        ProductPage pageMamothD300 = new ProductPage(driver, Pages.LAPTOP_PRODUCT_PAGE);
        pageMamothD300.open();
        pageMamothD300.getQuantityInputField().sendKeys(Keys.BACK_SPACE);
        pageMamothD300.getQuantityInputField().sendKeys("2");
        pageMamothD300.getAddToCartButton().click();
        pageMamothD300.pause(2L);

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.open();
        shoppingCartPage.getCheckoutButton().click();
    }

}
