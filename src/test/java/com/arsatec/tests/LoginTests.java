package com.arsatec.tests;

import com.arsatec.config.Config;
import com.arsatec.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    
    private WebDriver driver;
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setUp() {
        driver = Config.createDriver();
        loginPage = new LoginPage(driver);
    }
    
    @Test
    @Description("Login exitoso con credenciales válidas")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidLogin() {
        loginPage.login("testuser", "Password123!");
        
        String successMessage = loginPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("testuser"), 
            "Mensaje de éxito no contiene username esperado");
    }
    
    @Test
    @Description("Login fallido con credenciales inválidas")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidLogin() {
        loginPage.login("invalid", "wrongpass");
        
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Wrong"), 
            "No se mostró mensaje de error esperado");
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
