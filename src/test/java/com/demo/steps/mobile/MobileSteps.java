package com.demo.steps.mobile;

import com.demo.context.TestContext;
import com.demo.pages.mobile.MobileBrowserPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class MobileSteps {
    private final TestContext context;
    private MobileBrowserPage browserPage;

    public MobileSteps(TestContext context) {
        this.context = context;
    }

    private MobileBrowserPage browserPage() {
        if (browserPage == null) {
            browserPage = new MobileBrowserPage(context.getDriver(), context.getExplicitWaitSeconds());
        }
        return browserPage;
    }

    @Given("Safari is launched on the mobile simulator")
    public void safariIsLaunchedOnTheMobileSimulator() {
        assertThat(context.getDriver()).as("Appium driver should be initialized").isNotNull();
    }

    @When("I open the Google home page in Safari")
    public void iOpenTheGoogleHomePageInSafari() {
        browserPage().openUrl("https://www.google.com");
    }

    @Then("the Safari page title should contain {string}")
    public void theSafariPageTitleShouldContain(String expectedTitle) {
        assertThat(browserPage().getPageTitle()).containsIgnoringCase(expectedTitle);
    }
}
