@smoke
@navigation
Feature: Navigation Smoke Tests

  Scenario: Opening the Statistics and Tasks screens
    Given I open the TODO app
    When I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    When I open the drawer
    And I click the Task List button
    And I check that tasks screen is opened
    And I close the app

  Scenario: Opening the pre-added task and going back
    Given I pre-add task with title "UI <- button" and description "Description"
    Given I open the TODO app
    When I open task with title "UI <- button"
    And I click the edit task button
    And I click the back button
    Then I check if Task Details title is displayed
    When I click the back button
    Then I check that tasks screen is opened
    When I open task with title "UI <- button"
    And I click the edit task button
    And I click the physical back button
    Then I check if Task Details title is displayed
    And I click the physical back button
    Then I check that tasks screen is opened
    And I close the app



