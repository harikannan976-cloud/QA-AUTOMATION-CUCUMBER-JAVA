@mobile
Feature: Mobile Login

  Scenario: Login to mobile application
    Given the mobile app is launched
    When I login to the mobile app with username "demoUser" and password "demoPassword"
