Feature: As a user I want to get ability to find client by name, and
  @listclients
  Scenario:
    Given open web site, and open list clients page
    When the user clicks on edit clients, and fill name on "Jonsons", name edited
    When the user clicks on edit clients, id client equals "23"
    Then the user clicks on link all clients, title page equals "List of client"
    Then the user fill input with name "Jonsons", and click find, get all clients with name
