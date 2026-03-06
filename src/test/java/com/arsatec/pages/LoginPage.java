package com.arsatec.pages;

import com.arsatec.utils.BrowserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class LoginPage {
    
    private BrowserActions actions;
    private final String URL = "https://demoqa.com/login";
    
    // Localizadores
    private final By USERNAME_INPUT = By.id("userName");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login");
    private final By SUCCESS_MESSAGE = By.id("name");
    private final By ERROR_MESSAGE = By.xpath("//div[@class='mr-2']/p");
    
    public LoginPage(WebDriver driver) {
        this.actions = new BrowserActions(driver);
        actions.navigateTo(URL);
    }
    
    public void login(String username, String password) {
        WebElement usernameField = actions.waitAndFind(USERNAME_INPUT);
        actions.typeText(usernameField, username);
        
        WebElement passwordField = actions.waitAndFind(PASSWORD_INPUT);
        actions.typeText(passwordField, password);
        
        WebElement loginBtn = actions.waitAndFind(LOGIN_BUTTON);
        actions.clickElement(loginBtn);
    }
    
    public String getSuccessMessage() {
        return actions.waitAndFind(SUCCESS_MESSAGE).getText();
    }
    
    public String getErrorMessage() {
        return actions.waitAndFind(ERROR_MESSAGE).getText();
    }
}
