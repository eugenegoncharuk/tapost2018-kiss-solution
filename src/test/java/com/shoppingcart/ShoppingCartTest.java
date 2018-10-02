package com.shoppingcart;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import qa.pages.Pages;
import qa.pages.ProductPage;
import qa.pages.ShoppingCartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class ShoppingCartTest extends FrameworkTest {

    ProductPage page;

    @DisplayName("Test add item to shopping cart")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void addItemToShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("1 item(s) - 80.00€"), "Verify shopping cart Label");
    }

    @DisplayName("Test add several items to shopping cart")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void addSeveralItemsToShoppingCart() {

        page = new ProductPage(getDriver(), Pages.TABLET_PRODUCT_PAGE);
        page.open();

        page.getQuantityInputField().sendKeys(Keys.BACK_SPACE);
        page.getQuantityInputField().sendKeys("2");
        page.getAddToCartButton().click();
        page.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("2 item(s) - 399.98€"), "Verify shopping cart Label");
    }

    @DisplayName("Test add items with required fields to shopping cart")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void addItemsWithRequiredFieldsToShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_EOS_5D_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        assertEquals("Select required!", page.getRequiredFieldMessage().getText(), "Verify required field error message");
        assertTrue(page.getShoppingCart().getText().contains("0 item(s) - 0.00€"), "Verify shopping cart Label - No Items");
    }

    @DisplayName("Test change item count on shopping cart")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void changeItemCountOnShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        page.getShoppingCart().click();
        page.getViewCartLink().click();
        page.pause(2L);

        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        cartPage.getCartQuantityInputField().sendKeys(Keys.BACK_SPACE);
        cartPage.getTitle().click();
        cartPage.getCartQuantityInputField().sendKeys("2");
        cartPage.getCartUpdateInputButton().click();
        cartPage.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("2 item(s) - 160.00€"), "Verify shopping cart Label");
    }

    @DisplayName("Test invalid Gift Voucher on shopping cart")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void invalidGiftVoucherOnShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        page.getShoppingCart().click();
        page.getViewCartLink().click();
        page.pause(2L);

        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        cartPage.getGiftVoucher().click();
        cartPage.getGiftVoucherInputField().sendKeys("Invalid Voucher");
        cartPage.getGiftVoucherSubmitButton().click();
        cartPage.pause(2L);

        assertTrue(cartPage.getWarningMessage().getText().contains("Warning: Gift Certificate is either invalid or the balance has been used up!"),
                "Verify invalid Gift voucher message");
    }

    @DisplayName("Test valid Gift Voucher on shopping cart")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void validGiftVoucherOnShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        page.getShoppingCart().click();
        page.getViewCartLink().click();
        page.pause(2L);

        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        cartPage.getGiftVoucher().click();
        cartPage.getGiftVoucherInputField().sendKeys("Valid Voucher");
        cartPage.getGiftVoucherSubmitButton().click();
        cartPage.pause(2L);

        assertEquals("77.00€", cartPage.getProductPriceSum().getText(), "Verify shopping cart Label");
        assertTrue(cartPage.getShoppingCart().getText().contains("1 item(s) - 77.00€"), "Verify shopping cart Label");
    }

}
