package com.github.romankhachko.articleCodeSamples.earlyTesting.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by roman on 2/23/17.
 */
public class WelcomePage {

    @FindBy(id = "some_text_id")
    private WebElement welcomeTextContainer;

    @FindBy(id = "some_link_id")
    private WebElement logoutLink;

    public boolean isLogoutLinkDisplayed() {
        return logoutLink.isDisplayed();
    }

    public boolean isWelcomeTextDisplayed() {
        return welcomeTextContainer.isDisplayed();
    }

    public boolean isPageDisplayed() {
        return isLogoutLinkDisplayed() && isWelcomeTextDisplayed();
    }
}
