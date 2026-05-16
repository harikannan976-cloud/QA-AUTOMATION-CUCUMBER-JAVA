@web @GUI
Feature: Web Smoke Test

  Scenario: Open Google and verify it launched
    Given I open the Google home page
    Then the page title should contain "Google"
    And the browser URL should contain "google.com"
