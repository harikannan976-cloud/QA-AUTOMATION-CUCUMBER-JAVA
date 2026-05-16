package com.demo.steps.web;

import com.demo.context.TestContext;
import com.demo.pages.web.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class WebSteps {
    private final TestContext context;
    private HomePage homePage;

    public WebSteps(TestContext context) {
        this.context = context;
    }

    private HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(context.getDriver(), context.getExplicitWaitSeconds());
        }
        return homePage;
    }

    @Given("I open the Google home page")
    public void iOpenTheGoogleHomePage() {
        homePage().openApplication("https://www.google.com");
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedText) {
        assertThat(context.getDriver().getTitle()).containsIgnoringCase(expectedText);
    }

    @Then("the browser URL should contain {string}")
    public void theBrowserUrlShouldContain(String expectedUrlPart) {
        assertThat(context.getDriver().getCurrentUrl()).containsIgnoringCase(expectedUrlPart);
    }
}
