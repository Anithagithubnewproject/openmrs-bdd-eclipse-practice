# OpenMRS Hybrid BDD Automation Framework

A hybrid **Cucumber + Selenium + REST Assured + TestNG** framework for **OpenMRS**,
built as a Behavior Driven Development (BDD) automation kata. Feature files express
business intent in Gherkin; step definitions coordinate; Page Objects own UI locators;
an encapsulated API client owns REST calls. Dynamic state (e.g. a patient's UUID
created via the API) is passed into UI verification steps via a shared, scenario-scoped
context object injected with PicoContainer.

> **Why a local Dockerized instance instead of the public demo?** The kata's target
> is `https://demo.openmrs.org/openmrs`. Running against a self-hosted, Dockerized
> instance instead gives deterministic, isolated test data (not a shared public
> environment anyone else could be modifying concurrently) and a stable target for CI.
> Since all URLs are externalized in `config.properties`, pointing this framework back
> at the public demo instead only requires changing two config values — no code changes.

## Tech Stack

| Layer | Tool |
|---|---|
| BDD runner | Cucumber-JVM 7.18 + `cucumber-testng` + `cucumber-picocontainer` |
| Test runner | TestNG 7.10 |
| UI automation | Selenium 4.23 (+ WebDriverManager) |
| API automation | REST Assured 5.4 |
| Build | Maven |
| Reporting | Cucumber HTML/JSON plugin |
| CI | GitHub Actions |

## Project Structure

