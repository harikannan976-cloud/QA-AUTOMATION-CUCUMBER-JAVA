package com.demo.steps.api;

import com.demo.api.ApiClient;
import com.demo.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {
    private final TestContext context;

    public ApiSteps(TestContext context) {
        this.context = context;
    }

    @Given("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        context.setApiResponse(ApiClient.get(context.getBaseApiUrl(), endpoint));
    }

    @Then("the API status code should be {int}")
    public void theApiStatusCodeShouldBe(int expectedStatusCode) {
        assertThat(context.getApiResponse()).as("API response must exist").isNotNull();
        assertThat(context.getApiResponse().statusCode()).isEqualTo(expectedStatusCode);
    }

    @Then("the response should contain field {string}")
    public void theResponseShouldContainField(String fieldName) {
        Object fieldValue = context.getApiResponse().jsonPath().get(fieldName);
        if (fieldValue == null) {
            throw new AssertionError("Expected JSON field '" + fieldName + "' to exist but it was null");
        }
    }
}
