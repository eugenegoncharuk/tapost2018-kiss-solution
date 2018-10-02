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

    @BeforeEach
    public void initPage() {
        page = new ProductPage(getDriver(), Pages.MAMOTH_D300_PAGE);
        page.open();
    }

    @DisplayName("Test add item to shopping cart")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void addItemToShoppingCart() {

        page.getAddToCartButton().click();
        page.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("1 item(s) - 80.00€"), "Verify shopping cart Label");
    }

    @DisplayName("Test add several items to shopping cart")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void addSeveralItemsToShoppingCart() {

        page.getQuantityInputField().sendKeys("2");
        page.getAddToCartButton().click();
        page.pause(2L);

        assertTrue(page.getShoppingCart().getText().contains("2 item(s) - 160.00€"), "Verify shopping cart Label");
    }

}
