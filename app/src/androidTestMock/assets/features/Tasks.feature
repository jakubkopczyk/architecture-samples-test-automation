@smoke
@tasks
Feature: Tasks Smoke Tests

  Scenario: Edit Task
    Given I pre-add task with title "Test Title" and description "Description"
    And I open the TODO app
    When I open task with title "Test Title"
    Then I check if task title in Task Details is "Test Title"
    And I check if task description in Task Details is "Description"
    And I check if task in Task Details is not completed
    When I click the edit task button
    And I enter the task title "New Title"
    And I enter the task description "New Description"
    And I click the save button
    Then I can see text "New Title"
    And I cannot see text "Test Title"
    And I close the app

  Scenario: Create invalid task
    Given I open the TODO app
    When I click the add task button
    And I click the save button
    Then I can see text "New Task"
    When I enter the task title "New Title"
    And I click the save button
    When I enter the task title "New Title"
    When I click the back button
    And I click the add task button
    And I enter the task description "New Description"
    And I click the save button
    When I enter the task title "New Title"

  Scenario: Create and delete the task
    Given I open the TODO app
    When I click the add task button
    And I enter the task title "New Task"
    And I enter the task description "New Description"
    And I click the save button
    Then I can see text "New Task"
    When I open task with title "New Task"
    And I click the task delete button in Task Details
    And I click the filter button
    And I click the button with text "All"
    Then I cannot see text "New Task"
    And I close the app

  Scenario: Create two tasks and delete one task
    Given I pre-add task with title "Test Task 1" and description "Description 1"
    And I pre-add task with title "Test Task 2" and description "Description 2"
    And I open the TODO app
    When I open task with title "Test Task 2"
    And I click the task delete button in Task Details
    And I click the filter button
    And I click the button with text "All"
    Then I cannot see text "Test Task 2"
    And I close the app

  Scenario: Mark task as completed in Task Details
    Given I pre-add task with title "Task to complete" and description "Description"
    And I open the TODO app
    When I open task with title "Task to complete"
    And i click the completion checkbox in Task Details
    And I click the back button
    Then I check if task with title "Task to complete" is marked as completed
    And I close the app

  Scenario: Mark task as active in Task Details
    Given I pre-add completed task with title "Completed Task" and description "Description"
    And I open the TODO app
    When I open task with title "Completed Task"
    And i click the completion checkbox in Task Details
    And I click the back button
    Then I check if task with title "Completed Task" is marked as active
    And I close the app

  Scenario: Double tap active task completion in Task Details
    Given I pre-add task with title "Active task to tap" and description "Description"
    And I open the TODO app
    When I open task with title "Active task to tap"
    And i click the completion checkbox in Task Details
    And i click the completion checkbox in Task Details
    And I click the back button
    Then I check if task with title "Active task to tap" is marked as active
    And I close the app

  Scenario: Double tap completed task completion in Task Details
    Given I pre-add completed task with title "Completed task to tap" and description "Description"
    And I open the TODO app
    When I open task with title "Completed task to tap"
    And i click the completion checkbox in Task Details
    And i click the completion checkbox in Task Details
    And I click the back button
    Then I check if task with title "Completed task to tap" is marked as completed
    And I close the app

  Scenario: Filter tasks
    Given I pre-add completed task with title "Completed task 1" and description "Description"
    And I pre-add completed task with title "Completed task 2" and description "Description"
    And I pre-add task with title "Active task 1" and description "Description"
    And I pre-add task with title "Active task 2" and description "Description"
    And I open the TODO app
    And I click the filter button
    And I click the button with text "Active"
    Then I can see text "Active task 1"
    And I can see text "Active task 1"
    And I cannot see text "Completed task 1"
    And I cannot see text "Completed task 2"

  Scenario: Clear completed
    Given I pre-add completed task with title "Completed task 1" and description "Description"
    And I pre-add completed task with title "Completed task 2" and description "Description"
    And I pre-add task with title "Active task 1" and description "Description"
    And I pre-add task with title "Active task 2" and description "Description"
    And I open the TODO app
    And I open context menu
    And I click the button with text "Clear completed"
    Then I can see text "Active task 1"
    And I can see text "Active task 1"
    And I cannot see text "Completed task 1"
    And I cannot see text "Completed task 2"