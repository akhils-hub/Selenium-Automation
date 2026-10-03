# 🚀 Advanced Selenium UI Automation Framework

An industry-grade, enterprise-ready UI automation testing framework built using **Java 21**, **Selenium 4 WebDriver**, and **TestNG**. This framework incorporates modern architectural design patterns to ensure maximum test stability, maintainability, and highly actionable reporting pipelines.

---

## 🛠️ Key Framework Features

* **Page Object Model (POM):** Achieves complete separation of concern by encapsulating UI elements and locators into dedicated page classes, shielding test scripts from UI layout changes [INDEX].
* **Global Property Manager:** Externalizes environment parameters (URLs, credentials, settings) into a centralized `.properties` file managed via highly efficient static initialization blocks [INDEX].
* **Unified Tracing Engine (Log4j 2):** Incorporates custom thread-safe wrapper methods (`logStep`) that simultaneously route runtime logs to the system console, rolling text log files, and HTML dashboards [INDEX].
* **Interactive HTML Analytics (ExtentReports 5):** Auto-generates a rich, dark-themed execution dashboard complete with charts, pass/fail timelines, category tagging, and execution stats [INDEX].
* **Automated Failure Screenshots:** Utilizes a custom TestNG Listener to monitor runtime states, capturing the exact browser viewport state on assertion or system failures before teardown routines execute [INDEX].

---

## 📁 Project Architecture & Directory Layout

```text
SeleniumAutomation/
├── src/
│   ├── main/java/             
│   │   
│   └── test/
│       ├── java/               # Test Suite Components
|       |   └── pageObjects/    # Encapsulated locators and interactive actions  # Application Page Objects
│       │   ├── BaseTestClass/  # Global hooks, drivers, and framework properties
│       │   ├── testCases/      # Organized regression script classes by module
│       │   └── utilities/      # TestNG Listeners and ExtentReport engines
│       └── resources/          # Configuration Assets
│           ├── config.properties
│           └── log4j2.xml
├── Reports/                    # Auto-generated HTML execution dashboards
├── Screenshots/                # Evidence captures mapped to failed steps
├── target/                     # Compiled binaries managed via Maven
├── testng.xml                  # Test suite suite execution manifest mapping
└── pom.xml                     # Maven project manifest managing all dependencies
```

---

## 🚀 Getting Started & Local Setup

### Prerequisites
* **Java Development Kit (JDK):** Version 11 or higher (**Java 21** recommended) [INDEX].
* **Build Tool:** Apache Maven installed and configured in your system environment path.
* **IDE:** Eclipse IDE (or IntelliJ IDEA).

### 🏃 Running the Tests

1. **Clone the repository** onto your local machine workspace:
   ```bash
   git clone https://github.com/akhils-hub/Selenium-Automation.git
   ```
2. Open the project inside **Eclipse IDE** as an existing Maven project.
3. To trigger the entire test execution suite, right-click the **`testng.xml`** file in your project root folder and select **Run As > TestNG Suite** [INDEX].

### 📊 Reviewing the Reports
After the test run completes, refresh your project folder (`F5`) inside Eclipse [INDEX]. Navigate to the `/Reports/` directory and open **`AutomationExecutionReport.html`** in any web browser to view your testing analytics [INDEX].
