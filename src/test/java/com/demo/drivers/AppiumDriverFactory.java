package com.demo.drivers;

import com.demo.config.AppiumConfiguration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class AppiumDriverFactory {
    private AppiumDriverFactory() {}

    public static WebDriver createMobileDriver(AppiumConfiguration configuration, int implicitWaitSeconds) {
        if (configuration.getServerUrl() == null || configuration.getServerUrl().isBlank()) {
            throw new IllegalArgumentException("Appium server URL is required");
        }
        if (configuration.getPlatformName() == null || configuration.getPlatformName().isBlank()) {
            throw new IllegalArgumentException("Mobile platform name is required");
        }
        if (configuration.getDeviceName() == null || configuration.getDeviceName().isBlank()) {
            throw new IllegalArgumentException("Mobile device name is required");
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", configuration.getPlatformName());
        caps.setCapability("deviceName", configuration.getDeviceName());
        caps.setCapability("automationName", configuration.getAutomationName());

        if (configuration.getBrowserName() != null && !configuration.getBrowserName().isBlank()) {
            caps.setCapability("browserName", configuration.getBrowserName());
        } else if (configuration.getAppPath() != null && !configuration.getAppPath().isBlank()) {
            caps.setCapability("app", configuration.getAppPath());
        } else {
            throw new IllegalArgumentException("Either mobile browser name or app path must be configured");
        }

        if (configuration.getPlatformVersion() != null && !configuration.getPlatformVersion().isBlank()) {
            caps.setCapability("platformVersion", configuration.getPlatformVersion());
        }
        if (configuration.getUdid() != null && !configuration.getUdid().isBlank()) {
            caps.setCapability("udid", configuration.getUdid());
        }
        if (configuration.getAppPackage() != null && !configuration.getAppPackage().isBlank()) {
            caps.setCapability("appPackage", configuration.getAppPackage());
        }
        if (configuration.getAppActivity() != null && !configuration.getAppActivity().isBlank()) {
            caps.setCapability("appActivity", configuration.getAppActivity());
        }

        try {
            String platform = configuration.getPlatformName().trim().toLowerCase();
            WebDriver driver;
            if (platform.contains("ios")) {
                driver = new IOSDriver(new URL(configuration.getServerUrl()), caps);
            } else {
                driver = new AndroidDriver(new URL(configuration.getServerUrl()), caps);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitSeconds));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + configuration.getServerUrl(), e);
        }
    }
}
