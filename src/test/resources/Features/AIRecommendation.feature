Feature: AI Recipe Recommendation

  Scenario: Recommend vegan recipe based on available ingredients
    Given the customer is vegan
    And has tomatoes, basil, and pasta
    And has 30 minutes available
    When the AI assistant is asked for a recipe
    Then it should recommend "Spaghetti with Tomato Sauce"
    And explain that it fits dietary needs and time limit