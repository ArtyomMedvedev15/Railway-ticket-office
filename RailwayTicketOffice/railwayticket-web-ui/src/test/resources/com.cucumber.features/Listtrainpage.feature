Feature: As a user I want to get ability to use add new train, and fill in fields with sending value
  @listtrain
  Scenario:
    Given open web site, and open list train page
    When the user clicks on add new train button, form with title "Add new train" appears
    When the user add new train, with all filed, new train appears
    When the user clicks on edit train, title train equals "23"
    Then the user clicks on link all train, title page equals "List of trains"

