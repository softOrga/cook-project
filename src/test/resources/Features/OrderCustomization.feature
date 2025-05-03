Feature: Order and Menu Customization

  Scenario: Create a custom meal
    Given the customer is creating a new meal request
    When the customer selects ingredients: "Tomatoes", "Basil", "Pasta"
    And saves the meal
    Then the system should validate ingredient compatibility
    And confirm the custom meal request

  Scenario: Suggest substitutions
    Given the customer selects an ingredient that is unavailable or restricted
    When the system detects this issue
    Then the system should suggest a suitable alternative
    And alert the chef for approval