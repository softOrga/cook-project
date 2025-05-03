Feature: Inventory and Supplier Management

  Scenario: Track and restock ingredients
    Given the system is monitoring stock levels
    When the stock of "Tomatoes" falls below threshold
    Then the system should suggest restocking

  Scenario: Integrate with suppliers
    Given the kitchen manager opens the purchasing interface
    When the system fetches real-time prices from suppliers
    And detects critical low stock
    Then it should automatically generate a purchase order