Feature: As a user I want to get ability to use menu, and fill in fields with sending value
  @home
  Scenario:
    Given the user opens website Railway ticket office
    When the user clicks on the filed with date, a date field appears
    When the user clicks on the filed with station, a list appears
    When try to send request with empty field, valid message appears
    Then form with title "Find your train!" is displayed

