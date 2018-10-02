package com.E2E;

import core.FrameworkTest;
import org.junit.jupiter.api.BeforeEach;
import qa.pages.UserLoginPage;
import qa.pages.UserPersonalAreaPage;

public class FullCheckoutProcess extends FrameworkTest {

    UserLoginPage userLoginPage;

    @BeforeEach
    public void initPage() {
        userLoginPage = new UserLoginPage(getDriver());
        userLoginPage.open();
        userLoginPage.getEmailInput().sendKeys("mytestathon@megamail.com");
        userLoginPage.getPasswordInput().sendKeys("qwas");
        userLoginPage.getLoginButton().click();
    }

}
