package com.categories;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.Extensions.selenium.SeleniumExtension;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import qa.pages.BrowseCategoriesPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class BrowseCategoriesTest extends FrameworkTest {

    private static final String LAPTOPS_NOTEBOOKS_PAGE = "18";
    private static final String DESKTOPS_PAGE = "20";
    private static final String COMPONENTS_PAGE = "25";
    private static final String TABLETS_PAGE = "57";
    private static final String SOFTWARE_PAGE = "17";
    private static final String PHONES_PDAS_PAGE = "24";
    private static final String CAMERAS_PAGE = "33";
    private static final String MP3_PLAYERS_PAGE = "34";

    @BeforeEach
    public void initPage() {
    }

    public static final String NUMBER_PATTERN = "\\(([0-9]+)\\)";

    @DisplayName("Products presented correctly")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 3, exceptions = SeleniumException.class)
    public void categoryProductsPresentedCorrectly() {
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(LAPTOPS_NOTEBOOKS_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(DESKTOPS_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(COMPONENTS_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(TABLETS_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(SOFTWARE_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(PHONES_PDAS_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(CAMERAS_PAGE));
        makeAssertionsOnLoadedCategoryPage(getPageByCategory(MP3_PLAYERS_PAGE));
    }

    private BrowseCategoriesPage getPageByCategory(String category) {
        BrowseCategoriesPage page = new BrowseCategoriesPage(getDriver(), category);
        page.openAndWaitToBeLoaded();

/*
        For future releases

        Actions actions = new Actions(driver);
        actions.moveToElement(page.getCategoryMenuByName(category));
        actions.moveToElement(page.getShowAllForCategory(category));

        actions.build().perform();*/

        return page;
    }

    private void makeAssertionsOnLoadedCategoryPage(BrowseCategoriesPage page) {
        page.getInputLimitDropDown().click();

        page.selectFromDropdown("100");

        final int productElementsCount = page.getProductElementsCount();
        assertTrue(productElementsCount > 0);

        String text = page.getActiveLeftMenu().getText();
        Pattern p = Pattern.compile(NUMBER_PATTERN);
        Matcher matcher = p.matcher(text);
        assertTrue(matcher.find());

        final int productCoundFromLeftMenu = Integer.parseInt(matcher.group(1));
        assertEquals(productCoundFromLeftMenu, productElementsCount);
    }

}
