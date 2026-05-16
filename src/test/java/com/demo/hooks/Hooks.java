package com.demo.hooks;

import com.demo.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before("@web")
    public void setupWeb() {
        context.initializeWebDriver();
    }

    @Before("@mobile")
    public void setupMobile() {
        context.initializeMobileDriver();
    }

    @After("@web or @mobile")
    public void tearDown() {
        context.cleanupDriver();
    }
}
