package com.github.romankhachko.articleCodeSamples.earlyTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 2/23/17.
 */
public class LoginFunctionalityTest {

    // For this example we don't care of driver initialization
    private WebDriver driver;
    private static final String SYSTEM_ENTRYPOINT = "http://some.url";

    public void loginShouldBeSuccessful() {
        driver.get(SYSTEM_ENTRYPOINT);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("username", "password");
        assertTrue(PageFactory.initElements(driver, WelcomePage.class).isPageDisplayed());
    }
}
