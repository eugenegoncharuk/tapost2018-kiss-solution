package com.shoppingcart;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
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

        page.getQuantityInputField().sendKeys("2");
        page.getAddToCartButton().click();
        page.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("2 item(s) - 399.98€"), "Verify shopping cart Label");
    }

}
