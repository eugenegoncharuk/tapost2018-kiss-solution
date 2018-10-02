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

    @DisplayName("Test Search by regular expression")
    @Tag("LOCAL")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void searchByRegExp() {

        page.getSearchInputField().sendKeys("boo");

        page.getSearchButton().click();
        page.pause(3L);

        assertEquals(6, page.getSearchResultsCount(), "Verify search results is not empty");
        assertTrue(page.getProductTitlesInResults().contains("BakBook Air"));
    }

    @DisplayName("Test Search in SubCategory with no results")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void searchInSubcategoryWithNoResults() {

        page.getSearchInputField().sendKeys("boo");

        page.getCategoryDropdown().click();
        page.selectFromDropdown("Windows");

        page.getSubCategoryCheckbox().click();

        page.getSearchButton().click();
        page.pause(3L);

        assertEquals("Your shopping cart is empty!", page.getEmptySearchResultMessage().getText(), "Verify search result is not empty");
    }

    @DisplayName("Test Search in SubCategory")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void searchInSubcategory() {

        page.getSearchInputField().sendKeys("tab");

        page.getCategoryDropdown().click();
        page.selectFromDropdown("Tablets");

        page.getSubCategoryCheckbox().click();

        page.getSearchButton().click();
        page.pause(3L);

        assertEquals(1, page.getSearchResultsCount(), "Verify search results is not empty");
        assertTrue(page.getProductTitlesInResults().contains("Space Tab 10.1"));
    }

    @DisplayName("Test Search in Product Description")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void searchInProductDescription() {

        page.getSearchInputField().sendKeys("ipsum");

        page.getDescriptionCheckbox().click();

        page.getSearchButton().click();
        page.pause(3L);

        assertTrue(page.getSearchResultsCount() > 0 , "Verify search results is not empty");
        assertTrue(page.getProductTitlesInResults().contains("BakBook Air"));
    }

    @DisplayName("Test Search Results Sorting")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void searchResultsSorting() {

        page.getSearchInputField().sendKeys("book");

        page.getSearchButton().click();
        page.pause(3L);

        page.getSortDropdown().click();
        page.selectFromDropdown("Price (High > Low)");
        page.pause(3L);

        assertTrue(page.getProductTitlesInResults().get(0).equals("BakBook Pro 2018"), "Verify First result after sorting");
    }

    @DisplayName("Test Search check Result Pagination")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void searchResultsPagination() {

        page.getSearchInputField().sendKeys("web");

        page.getSearchButton().click();
        page.pause(3L);

        assertTrue(page.getSearchResultsCount() > 0 , "Verify search results is not empty");
        assertTrue(page.getPagination().isDisplayed());
    }

}
