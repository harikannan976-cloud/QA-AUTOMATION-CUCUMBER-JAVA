@api
Feature: API Smoke Test

  Scenario: Validate Google DNS-over-HTTPS response
    Given I send a GET request to "/resolve?name=example.com&type=A"
    Then the API status code should be 200
    And the response should contain field "Status"
