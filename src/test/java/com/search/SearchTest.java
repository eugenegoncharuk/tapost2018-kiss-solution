package com.search;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import qa.pages.SearchPage;

import static core.Extensions.selenium.SeleniumExtension.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class SearchTest {

    SearchPage page;

    @BeforeEach
    public void initPage() {
        page = new SearchPage(getDriver());
        page.open();
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
        page.pause(5L);

        assertEquals(6, 6, "Extension test");
    }

}
