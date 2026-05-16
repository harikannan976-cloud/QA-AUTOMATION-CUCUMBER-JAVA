package com.demo.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public final class ApiClient {
    private ApiClient() {
        // Utility class
    }

    public static Response get(String baseUri, String endpoint) {
        return given()
                .baseUri(baseUri)
                .when()
                .get(endpoint)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }
}
