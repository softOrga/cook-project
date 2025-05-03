Feature: Scheduling and Task Management

  Scenario: Assign tasks to chefs
    Given the kitchen manager is reviewing chef workloads
    When the manager assigns a new cooking task to a chef
    Then the chef should receive a notification with task details

  Scenario: Chef receives cooking notification
    Given a cooking task has been assigned to a chef
    When the chef logs in
    Then the chef should see their list of tasks for the day