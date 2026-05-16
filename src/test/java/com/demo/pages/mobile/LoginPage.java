package com.demo.pages.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final By usernameField = By.id("username");
    private static final By passwordField = By.id("password");
    private static final By loginButton = By.id("login");
    private static final By homeScreenLabel = By.id("home_screen_label");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, int explicitWaitSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitSeconds));
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isHomeScreenDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(homeScreenLabel)).isDisplayed();
    }
}
