package com.demo.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public final class WebDriverFactory {
    private WebDriverFactory() {}

    public static WebDriver createDriver(String browser, int implicitWaitSeconds, boolean headless, boolean maximize, String windowSize, boolean disableWebSecurity, boolean disableExtensions) {
        String normalizedBrowser = browser.trim().toLowerCase();
        WebDriver driver;

        switch (normalizedBrowser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                if (maximize) {
                    options.addArguments("--start-maximized");
                }
                if (windowSize != null && !windowSize.isBlank()) {
                    options.addArguments("--window-size=" + windowSize);
                }
                options.addArguments("--remote-allow-origins=*");
                if (disableWebSecurity) {
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");
                }
                if (disableExtensions) {
                    options.addArguments("--disable-extensions");
                }
                driver = new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitSeconds));
        driver.manage().window().maximize();
        return driver;
    }
}
