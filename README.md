# Cucumber BDD Automation – Demo Web Shop

A UI test automation project built with **Java, Selenium WebDriver, Cucumber (BDD), TestNG, and Maven**. The project automates selected user journeys on the public Demo Web Shop website and uses Selenium Grid for remote browser execution.

## Test coverage

The current feature files cover:

| Feature tag | What it validates |
|---|---|
| `@websiteLaunch` | Opens the Demo Web Shop website and validates the page title/navigation. |
| `@searchItem` | Searches for `laptop` and checks that product results are displayed. |
| `@sortItems` | Opens the Books category and validates sorting in **A to Z** and **Z to A** order. |

The scenarios are written in Gherkin and connected to Java step definitions through Cucumber.

## Technology stack

- Java 17 (configured in `pom.xml`)
- Maven
- Selenium WebDriver 4.46.0
- Cucumber Java / Cucumber TestNG 7.34.2
- TestNG 7.12.0
- Selenium Grid (Selenium Server 4.51.0 JAR included in the project resources)
- Log4j2 for logging
- Allure Cucumber adapter for report data and attachments

## Project structure

```text
Cucumber_BDD_Automation_Assignment/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── Base.java
│   │   │   └── data.properties
│   │   └── resources/
│   │       ├── log4j2.xml
│   │       └── selenium-server-4.51.0.jar
│   └── test/
│       ├── java/
│       │   ├── cucumberOptions/
│       │   │   └── TestRunner.java
│       │   ├── hooks/
│       │   │   └── ScenarioHooks.java
│       │   └── stepDefinitions/
│       │       ├── validateSearch.java
│       │       ├── validateSortItems.java
│       │       └── validateWebsiteLaunch.java
│       └── resources/
│           └── features/
│               ├── validateSearch.feature
│               ├── validateSortItems.feature
│               └── validateWebsiteLaunch.feature
└── README.md
```

### Framework design

- **Base class:** Loads test configuration and stores the WebDriver reference using `ThreadLocal`.
- **Page Objects:** `SearchPage` and `SortByPage` keep page locators and page interactions separate from step definitions.
- **Step definitions:** Implement the actions and assertions described by the Gherkin features.
- **Cucumber hooks:** Create and close browser sessions and attach screenshots to Allure for scenarios that pass, as currently implemented.
- **Test runner:** Connects Cucumber features to the `hooks` and `stepDefinitions` packages. Its scenario data provider is configured for parallel execution.
- **TestNG suite:** `testng.xml` configures the suite with a thread count of 5.
- **Logging:** Log4j2 is configured to log to the console and to `logs/prints.log`.

## Prerequisites

Install or configure the following on the machine running the tests:

1. **JDK 17 or later** (the Maven compiler release is set to 17).
2. **Apache Maven** and access to download Maven dependencies.
3. A supported browser: Chrome, Edge, or Firefox.
4. Selenium Grid running locally at `http://localhost:4444`.
5. A compatible browser installed on the Grid node that will execute the tests.

> The current hook creates `RemoteWebDriver` sessions against `http://localhost:4444`; running the tests without a reachable Grid will not work with the current configuration.

## Configuration

Update `src/main/java/data.properties` as needed:

```properties
url=https://demowebshop.tricentis.com/
browser=chrome
```

The configured browser value selects the browser requested from Grid. The current setup supports `chrome`, `edge`, and Firefox as the fallback branch. Make sure the selected browser is available on the Grid node.

**Note:** The current `Base.java` loads `data.properties` using a Windows-style file path. If you run the project on a non-Windows operating system, update that path handling before running the tests.

## Run the tests

### 1. Start Selenium Grid

Open a terminal at the **project root** (the folder containing `pom.xml`) and run:

```bash
java -jar src/main/resources/selenium-server-4.51.0.jar standalone
```

Wait for Grid to start. The Grid UI is normally available at `http://localhost:4444/ui`.

### 2. Execute the test suite

Open a second terminal at the same project root and run:

```bash
mvn clean test
```

Maven resolves the dependencies declared in `pom.xml`, compiles the project, and executes the configured TestNG/Cucumber suite. The runner is configured to execute the three feature tags listed above, with Cucumber scenarios eligible for parallel execution.

### Run a specific feature by tag

To run one feature, temporarily change the `tags` expression in `src/test/java/cucumberOptions/TestRunner.java`. Examples:

```java
tags = "@websiteLaunch"
```

```java
tags = "@searchItem"
```

```java
tags = "@sortItems"
```

Restore the full expression after a focused run:

```java
tags = "@websiteLaunch or @searchItem or @sortItems"
```

## Reports and logs

- **Console output:** Maven/Cucumber execution details appear in the terminal.
- **Log file:** Log4j2 is configured to write to `logs/prints.log` relative to the project working directory.
- **Allure results:** The Cucumber runner uses the Allure Cucumber adapter. After a run, result files can be used to generate an Allure report if the Allure command-line tool is installed.

For example, if the `allure-results` directory has been generated and the Allure CLI is installed:

```bash
allure serve allure-results
```

## Repository hygiene

Before pushing to GitHub, keep the repository focused on source code and setup instructions. Do not commit generated or machine-specific output such as `target/`, log files, generated reports, IDE metadata, or local secrets. A `.gitignore` file at the project root is recommended. Do not publish real usernames, passwords, access tokens, or other sensitive test data.

## Scope and limitations

This is a learning/assignment automation framework for the public Demo Web Shop site. The automated checks are limited to the scenarios listed under **Test coverage**; they are not a claim of complete application coverage. Browser execution depends on a working local Selenium Grid and compatible browser setup.
