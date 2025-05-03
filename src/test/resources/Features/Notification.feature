Feature: Notifications and Alerts

  Scenario: Remind customers about upcoming deliveries
    Given the customer has a meal scheduled for delivery today
    When the time is 1 hour before delivery
    Then the system should send a reminder notification

  Scenario: Notify low stock
    Given the stock of an ingredient is low
    Then the kitchen manager should receive a low-stock alert