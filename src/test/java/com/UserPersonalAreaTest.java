package com;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import qa.pages.UserLoginPage;
import qa.pages.UserPersonalAreaPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPersonalAreaTest extends FrameworkTest {

    UserLoginPage userLoginPage;
    UserPersonalAreaPage userPersonalAreaPage;

    @BeforeEach
    public void initPage() {
        userLoginPage = new UserLoginPage(getDriver());
        userLoginPage.open();
        userLoginPage.getEmailInput().sendKeys("mytestathon@megamail.com");
        userLoginPage.getPasswordInput().sendKeys("qwas");
        userLoginPage.getLoginButton().click();
    }

    @DisplayName("Check personal area submenu")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void checkPersonalAreaSubMenu() {

        userPersonalAreaPage = new UserPersonalAreaPage(driver);

        assertEquals(12, userPersonalAreaPage.getPersonalAreaSubMenu().size(),
                "Personal area submenu is displayed");
    }

}
