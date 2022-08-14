@smoke
@statistics
Feature: Statistics Smoke Tests

  Scenario: Statistics - delete completed tasks first, then active
    Given I pre-add completed task with title "Completed task 1" and description "Description"
    And I pre-add completed task with title "Completed task 2" and description "Description"
    And I pre-add task with title "Active task 1" and description "Description"
    And I pre-add task with title "Active task 2" and description "Description"
    And I open the TODO app
    When I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 50.0%"
    And I can see text "Completed tasks: 50.0%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Completed task 1"
    And I click the task delete button in Task Details
    And I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 66.7%"
    And I can see text "Completed tasks: 33.3%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Completed task 2"
    And I click the task delete button in Task Details
    And I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 100.0%"
    And I can see text "Completed tasks: 0.0%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Active task 1"
    And I click the task delete button in Task Details
    Then I can see no tasks element with text "You have no tasks!"
    When I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 100.0%"
    And I can see text "Completed tasks: 0.0%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Active task 2"
    And I click the task delete button in Task Details
    And I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "You have no tasks."

  Scenario: Statistics - delete active tasks first, then completed
    Given I pre-add completed task with title "Completed task 1" and description "Description"
    And I pre-add completed task with title "Completed task 2" and description "Description"
    And I pre-add task with title "Active task 1" and description "Description"
    And I pre-add task with title "Active task 2" and description "Description"
    And I open the TODO app
    When I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 50.0%"
    And I can see text "Completed tasks: 50.0%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Active task 1"
    And I click the task delete button in Task Details
    And I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 33.3%"
    And I can see text "Completed tasks: 66.7%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Active task 2"
    And I click the task delete button in Task Details
    And I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 0.0%"
    And I can see text "Completed tasks: 100.0%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Completed task 1"
    And I click the task delete button in Task Details
    And I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "Active tasks: 0.0%"
    And I can see text "Completed tasks: 100.0%"
    When I open the drawer
    And I click the Task List button
    And I open task with title "Completed task 2"
    And I click the task delete button in Task Details
    Then I can see text "You have no tasks!"
    When I open the drawer
    And I click the Statistics button
    Then I check that statistics screen was opened
    And I can see text "You have no tasks."