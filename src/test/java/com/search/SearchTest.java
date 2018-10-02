package com.search;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import qa.pages.SearchPage;

import static core.Extensions.selenium.SeleniumExtension.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class SearchTest extends FrameworkTest {

    SearchPage page;

    @BeforeEach
    public void initPage() {
        page = new SearchPage(getDriver());
        page.open();
    }

    @DisplayName("Test Search in SubCategory with no results")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 3, exceptions = SeleniumException.class)
    public void searchInSubcategoryWithNoResults() {

        page.waitToBeLoaded();

        page.getSearchInputField().click();
        page.sendKeys("aaa");

        page.getCategoryDropdown().click();
        page.selectFromDropdown("PC");

        page.getSubCategoryCheckbox().click();

        page.getSearchButton().click();
        page.pause(3L);

        assertEquals("Your shopping cart is empty!", page.getEmptySearchResultMessage().getText(), "Verify search result is not empty");
    }

    @DisplayName("Test Search in SubCategory")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 3, exceptions = SeleniumException.class)
    public void searchInSubcategory() {

        page.waitToBeLoaded();

        page.getSearchInputField().click();
        page.sendKeys("tab");

        page.getCategoryDropdown().click();
        page.selectFromDropdown("Tablets");

        page.getSubCategoryCheckbox().click();

        page.getSearchButton().click();
        page.pause(3L);

        assertEquals(1, page.getSearchResultsCount(), "Verify search result is not empty");
        assertTrue(page.getProductTitlesInResults().contains("Space Tab 10.1"));
    }

}
