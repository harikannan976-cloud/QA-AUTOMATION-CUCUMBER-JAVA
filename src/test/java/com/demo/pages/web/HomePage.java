package com.demo.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By heading = By.cssSelector("h1");

    public HomePage(WebDriver driver, int explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
    }

    public void openApplication(String url) {
        open(url);
    }

    public String getHeadingText() {
        return text(heading);
    }
}
