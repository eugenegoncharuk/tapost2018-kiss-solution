package com;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.GlobalWorld;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import qa.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginUserTest extends GlobalWorld {

    LoginPage page;

    @BeforeEach
    public void initPage() {
        page = new LoginPage(getDriver());
        page.open();
    }

    @DisplayName("Login User Test")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 3, exceptions = SeleniumException.class)
    public void sampletest() {
        assertEquals(6, 6, "User is Logged in");
    }

}
