package com.demo.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileBrowserPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MobileBrowserPage(WebDriver driver, int explicitWaitSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitSeconds));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return wait.until(driver -> driver.getTitle() != null && !driver.getTitle().isBlank() ? driver.getTitle() : null);
    }
}
