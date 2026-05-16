# QA Automation Cucumber Java Framework

**Created by Hari Kannan** to demonstrate hands-on QA automation engineering skills.
This project was built entirely from scratch — every line of code reflects real-world experience and craftsmanship.

A robust automation framework using **Java, Gradle, Cucumber, Selenium, Appium, and Rest Assured**.

This project is designed for interview demos and portfolio showcase. It demonstrates a clean, scalable automation architecture with dependency injection, shared test context, and reusable page objects.

## What This Project Shows

- Web automation using Selenium WebDriver and WebDriverManager
- API automation using Rest Assured
- Mobile Safari automation on simulator using Appium
- BDD test scenarios written in Cucumber Gherkin
- Dependency injection with Cucumber PicoContainer and a shared `TestContext`
- Centralized driver lifecycle and configuration management
- Page Object Model for cleaner UI automation
- Configurable Gradle tasks for targeted execution
- Cucumber HTML/JSON reporting

## Architecture Highlights

- `com.demo.context.TestContext` stores shared test state
- `com.demo.api.ApiClient` centralizes API request handling
- `com.demo.drivers.WebDriverFactory` and `AppiumDriverFactory` create platform drivers
- `com.demo.hooks.Hooks` initializes and tears down drivers per scenario
- Page objects accept driver instances instead of using static manager access
- Steps classes receive `TestContext` through dependency injection

## Tech Stack

- Java 17
- Gradle
- Cucumber 7
- Selenium 4
- Appium Java Client
- Rest Assured
- JUnit Platform
- WebDriverManager

## Project Structure

```text
src/test/java/com/demo
 ├── api
 ├── config
 ├── context
 ├── drivers
 ├── hooks
 ├── pages
 │   ├── mobile
 │   └── web
 ├── runners
 └── steps
     ├── api
     ├── mobile
     └── web

src/test
 ├── features
 │   ├── api
 │   ├── mobile
 │   └── web
 └── resources
     ├── appium.properties
     └── config.properties
```

## Setup

1. Clone this repository.
2. Configure values in `src/test/resources/config.properties`.
3. For mobile tests, configure Appium simulator values in `src/test/resources/appium.properties`.
4. For web tests, choose a supported browser in `browser=`.
5. For mobile tests, ensure Appium is installed and running.

## How to Run

Run all tests:

```bash
GRADLE_USER_HOME=/tmp/gradle-user-home ./gradlew test
```

If you are on macOS and the project is stored under `~/Documents`, use a writable Gradle user home directory to avoid macOS privacy restrictions:

```bash
GRADLE_USER_HOME=/tmp/gradle-user-home ./gradlew test
```

Run only web tests:

```bash
./gradlew webTest
```

Run web tests in headed mode:

```bash
GRADLE_USER_HOME=/tmp/gradle-user-home ./gradlew webTest --no-daemon
```

Keep the browser open after the web scenario completes for local debugging:

```bash
GRADLE_USER_HOME=/tmp/gradle-user-home ./gradlew webTest --no-daemon -Dkeep.browser.open=true
```

If Chrome still does not appear, confirm that your config is set to `browser.headless=false` and that you are running from a local desktop environment rather than a headless or remote terminal session.

Run only API tests:

```bash
./gradlew apiTest
```

Run only mobile Safari tests:

```bash
./gradlew mobileTest
```

Run the HTML report helper command after tests (optional):

```bash
./gradlew cucumberHtmlReport
```

### Notes for Mobile

- Start the Appium server before running mobile scenarios:

```bash
appium
```

- Configure Appium simulator settings in:

```text
src/test/resources/appium.properties
```

- Configure mobile login credentials in:

```text
src/test/resources/config.properties
```

- API tests use the GitHub REST API base URL from `src/test/resources/config.properties`.

## Configuration

Edit these files for environment and test settings:

```text
src/test/resources/config.properties
src/test/resources/appium.properties
```

### GUI Configuration for Web Testing

The following properties control browser GUI behavior:

```properties
# Browser GUI settings
browser.headless=false          # Run in headless mode (default: false)
browser.maximize=true           # Maximize browser window (default: true)
browser.window.size=1920,1080   # Window size if not maximized (default: 1920x1080)
browser.disable.web.security=false  # Disable web security for CORS (default: false)
browser.disable.extensions=false    # Disable browser extensions (default: false)
```

For debugging with visible browser:

```bash
# Keep browser open after test completion
./gradlew webTest --no-daemon -Dkeep.browser.open=true
```

## Reports

After execution, Cucumber HTML and JSON reports are generated under:

```text
build/reports/cucumber/html
build/reports/cucumber/cucumber-report.json
build/reports/cucumber/cucumber-results.xml
```

## CI/CD — GitHub Actions

Every push to any branch and every pull request targeting `main` automatically triggers the test pipeline.

### Jobs

| Job | Runner | Trigger | Description |
|-----|--------|---------|-------------|
| **API Tests** | ubuntu-latest | push / PR | Runs all `@api` tagged scenarios against the REST API |
| **Web Tests** | ubuntu-latest | push / PR | Runs all `@web` tagged scenarios using headless Chrome |

> Mobile tests require a macOS runner with Appium and an iOS simulator. They are excluded from CI and should be run locally.

### What the pipeline does

1. Checks out the repository
2. Sets up Java 17 (Temurin)
3. Restores Gradle dependency cache (speeds up subsequent runs)
4. Runs the appropriate Gradle task
5. Uploads Cucumber HTML/JSON reports as downloadable artifacts (retained 14 days)

### Downloading reports

After a workflow run completes, open the run on GitHub → **Artifacts** → download `api-test-reports` or `web-test-reports`. The HTML report is at `html/index.html` inside the archive.

### Workflow file

```text
.github/workflows/gradle-tests.yml
```

## Example Commands

```bash
./gradlew clean test
./gradlew webTest
./gradlew apiTest
./gradlew mobileTest
```

## Using This in an Interview

This framework is ideal for demonstrating:

- modern automation architecture
- dependency injection with Cucumber
- hybrid testing across web, mobile, and API layers
- reusable page objects and hooks
- environment-driven configuration
- Gradle task execution and reporting
