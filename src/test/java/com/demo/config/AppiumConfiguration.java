package com.demo.config;

public class AppiumConfiguration {
    private final String serverUrl;
    private final String platformName;
    private final String platformVersion;
    private final String deviceName;
    private final String udid;
    private final String automationName;
    private final String browserName;
    private final String appPath;
    private final String appPackage;
    private final String appActivity;

    public AppiumConfiguration(
            String serverUrl,
            String platformName,
            String platformVersion,
            String deviceName,
            String udid,
            String automationName,
            String browserName,
            String appPath,
            String appPackage,
            String appActivity) {
        this.serverUrl = serverUrl;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.deviceName = deviceName;
        this.udid = udid;
        this.automationName = automationName;
        this.browserName = browserName;
        this.appPath = appPath;
        this.appPackage = appPackage;
        this.appActivity = appActivity;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getUdid() {
        return udid;
    }

    public String getAutomationName() {
        return automationName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getAppPath() {
        return appPath;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }
}
