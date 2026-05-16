package com.demo.context;

import com.demo.config.AppiumConfiguration;
import com.demo.drivers.AppiumDriverFactory;
import com.demo.drivers.WebDriverFactory;
import com.demo.utils.TestDataUtil;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestContext {
    private final Properties config = new Properties();
    private final Map<String, Object> scenarioState = new HashMap<>();
    private final TestDataUtil testData = new TestDataUtil();

    private WebDriver driver;
    private Response apiResponse;

    public TestContext() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        List<String> supportedFiles = Arrays.asList("config.properties", "appium.properties");
        for (String fileName : supportedFiles) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
                if (inputStream != null) {
                    config.load(inputStream);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to load " + fileName, e);
            }
        }

        if (config.isEmpty()) {
            throw new IllegalStateException("No configuration files found in resources");
        }
    }

    public String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }
        String value = config.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return value.trim();
    }

    public String getOrNull(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }
        return config.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public String getBaseWebUrl() {
        return get("base.web.url");
    }

    public String getBaseApiUrl() {
        return get("base.api.url");
    }

    public String getBrowser() {
        return get("browser");
    }

    public AppiumConfiguration getAppiumConfiguration() {
        return new AppiumConfiguration(
                get("appium.server.url"),
                get("mobile.platform.name"),
                getOrNull("mobile.platform.version"),
                get("mobile.device.name"),
                getOrNull("mobile.udid"),
                getOrNull("mobile.automation.name") == null ? "UiAutomator2" : getOrNull("mobile.automation.name"),
                getOrNull("mobile.browser.name"),
                getOrNull("mobile.app.path"),
                getOrNull("mobile.app.package"),
                getOrNull("mobile.app.activity")
        );
    }

    public String getMobileUsername() {
        return get("mobile.login.username");
    }

    public String getMobilePassword() {
        return get("mobile.login.password");
    }

    public int getImplicitWaitSeconds() {
        return getInt("implicit.wait.seconds");
    }

    public int getExplicitWaitSeconds() {
        return getInt("explicit.wait.seconds");
    }

    public boolean shouldKeepBrowserOpen() {
        String keepOpen = getOrNull("keep.browser.open");
        return keepOpen != null && Boolean.parseBoolean(keepOpen);
    }

    public boolean isHeadlessMode() {
        String headless = getOrNull("browser.headless");
        return headless != null && Boolean.parseBoolean(headless);
    }

    public boolean shouldMaximizeWindow() {
        String maximize = getOrNull("browser.maximize");
        return maximize == null || Boolean.parseBoolean(maximize);
    }

    public String getWindowSize() {
        return getOrNull("browser.window.size");
    }

    public boolean shouldDisableWebSecurity() {
        String disableSecurity = getOrNull("browser.disable.web.security");
        return disableSecurity != null && Boolean.parseBoolean(disableSecurity);
    }

    public boolean shouldDisableExtensions() {
        String disableExtensions = getOrNull("browser.disable.extensions");
        return disableExtensions != null && Boolean.parseBoolean(disableExtensions);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void initializeWebDriver() {
        this.driver = WebDriverFactory.createDriver(
            getBrowser(),
            getImplicitWaitSeconds(),
            isHeadlessMode(),
            shouldMaximizeWindow(),
            getWindowSize(),
            shouldDisableWebSecurity(),
            shouldDisableExtensions()
        );
    }

    public void initializeMobileDriver() {
        this.driver = AppiumDriverFactory.createMobileDriver(getAppiumConfiguration(), getImplicitWaitSeconds());
    }

    public void setApiResponse(Response apiResponse) {
        this.apiResponse = apiResponse;
    }

    public Response getApiResponse() {
        return apiResponse;
    }

    public void putState(String key, Object value) {
        scenarioState.put(key, value);
    }

    public Object getState(String key) {
        return scenarioState.get(key);
    }

    public String generateUniqueEmail() {
        return "testuser_" + Instant.now().toEpochMilli() + "@example.com";
    }

    public TestDataUtil getTestData() {
        return testData;
    }

    public void cleanupDriver() {
        if (driver != null && !shouldKeepBrowserOpen()) {
            driver.quit();
            driver = null;
        }
    }
}
