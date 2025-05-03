Feature: Customer Profile Management

  Scenario: Store dietary preferences and allergies
    Given the customer opens the dietary preferences form
    When the customer inputs "Vegan" and selects "Peanut allergy"
    And saves the information
    Then the system should store the preferences and allergies
    And the chef should be able to view this information

  Scenario: View past orders
    Given the customer logs into the system
    When the customer navigates to their order history
    Then the system should display a list of previous orders

  Scenario: Suggest personalized meal plans
    Given the chef accesses the customer's profile
    When the chef views order history
    Then the system should generate personalized meal suggestions