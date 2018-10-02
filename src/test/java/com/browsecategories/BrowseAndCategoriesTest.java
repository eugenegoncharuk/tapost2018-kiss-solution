package com.browsecategories;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.FrameworkTest;
import core.remote.SeleniumException;
import core.utils.PageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import qa.pages.CategoriesPage;

@ExtendWith(SeleniumExtension.class)
public class BrowseAndCategoriesTest extends FrameworkTest {

    private static final String TABLETS_PAGE = "57";
    private static final String COMPONENTS_PAGE = "17";

    CategoriesPage page;

    @BeforeEach
    public void initPage() {
        page = new CategoriesPage(getDriver(), TABLETS_PAGE);
        page.open();
    }

    @DisplayName("Product present in Category without subcategories")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 3, exceptions = SeleniumException.class)
    public void productPresentCategoryTest() {
        page.waitToBeLoaded();

        page.getInputLimitDropDown().click();;
        page.selectFromDropdown("100");

    }

}
