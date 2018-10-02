package com.user;

import core.Extensions.repeater.RepeatedIfExceptionsTest;
import core.FrameworkTest;
import core.remote.SeleniumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import qa.pages.UserRegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationTest extends FrameworkTest {

    UserRegistrationPage page;

    @BeforeEach
    public void initPage() {
        page = new UserRegistrationPage(getDriver());
        page.open();
    }

    @DisplayName("Check user registration form")
    @Tag("LOCALHOST")
    @RepeatedIfExceptionsTest(repeats = 2, exceptions = SeleniumException.class)
    public void userRegistrationTest() {

        //input personal details info
        page.getFirstNameInput().sendKeys("Firstname");
        page.getLastNameInput().sendKeys("LastName");
        page.getEmailInput().sendKeys("mymail@testathon.com");
        page.getPhoneInput().sendKeys("555-35-35");
        //input password input info
        page.getPasswordInput().sendKeys("qwerty");
        page.getConfirmPasswordInput().sendKeys("qwerty");
        //agree with Policy terms
        page.getAgreePolicyCheckbox().click();

        page.getContinueButton().click();

        assertEquals("Congratulations! Your new account has been successfully created!", page.getResultMessages().get(1).getText(),
                "User is created with message");

    }

}
