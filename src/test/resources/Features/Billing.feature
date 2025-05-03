Feature: Billing System

  Scenario: Generate invoice
    Given the customer completes an order
    When the order is finalized
    Then the system should generate and send an invoice

  Scenario: Financial report generation
    Given the administrator logs into the system
    When financial data is requested
    Then the system should display up-to-date financial reports