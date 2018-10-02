package com.user;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import qa.pages.UserLoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginTest extends FrameworkTest {

    UserLoginPage page;

    @BeforeEach
    public void initPage() {
        page = new UserLoginPage(getDriver());
        page.open();
    }

    @DisplayName("Check user is logged in")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void userLogintest() {
        page.getEmailInput().sendKeys("mytestathon@megamail.com");
        page.getPasswordInput().sendKeys("qwas");
        page.getLoginButton().click();

        page.getEditAccount().click();

        assertEquals("KISS_TESTER", page.getFirstNameInput().getAttribute("value"),
                "Verify is logged with correct Name");
        assertEquals("TESTATHON", page.getLastNameInput().getAttribute("value"),
                "Verify is logged with correct LastName");
    }

}
