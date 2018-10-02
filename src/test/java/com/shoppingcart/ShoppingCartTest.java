package com.shoppingcart;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import qa.pages.Pages;
import qa.pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class ShoppingCartTest extends FrameworkTest {

    ProductPage page;

    @DisplayName("Test add item to shopping cart")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void addItemToShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("1 item(s) - 80.00€"), "Verify shopping cart Label");
    }

    @DisplayName("Test add several items to shopping cart")
    @Tag("LOCAL")
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
    @Tag("LOCAL")
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
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void changeItemCountOnShoppingCart() {

        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PRODUCT_PAGE);
        page.open();

        page.getAddToCartButton().click();
        page.pause(2L);

        page.getShoppingCart().click();
        page.getViewCartLink().click();
        page.pause(2L);

        page.getCartQuantityInputField().sendKeys(Keys.BACK_SPACE);
        page.getQuantityInputField().sendKeys("2");
        page.getCartUpdateInputButton().click();

        assertTrue(page.getShoppingCart().getText().contains("2 item(s) - 399.98€"), "Verify shopping cart Label");
    }

}
