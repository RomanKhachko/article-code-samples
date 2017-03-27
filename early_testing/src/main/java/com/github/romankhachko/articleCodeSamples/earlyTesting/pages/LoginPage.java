package com.github.romankhachko.articleCodeSamples.earlyTesting.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by roman on 2/23/17.
 */
public class LoginPage {

    @FindBy(id = "some_field_id")
    private WebElement usernameField;

    @FindBy(id = "some_field_id_2")
    private WebElement passwordField;

    @FindBy(id = "some_btn_id_2")
    private WebElement loginBtn;

    public void enterUsername(String userName) {
        usernameField.sendKeys(userName);
    }

    public void enterPassword(String password) {
        usernameField.sendKeys(password);
    }

    public void pressLoginBtn() {
        loginBtn.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        pressLoginBtn();
    }
}
