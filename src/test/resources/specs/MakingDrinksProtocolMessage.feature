Feature: Drink Maker Protocol

  Orders from customers of the coffee machine are translated into a specific protocol in order
  to be understandable by the drink maker.

  The coffee machine can serves 3 type of drinks: tea, coffee, chocolate.
  The coffee machine can add one or two sugars to the drink.
  If an order contains sugar, then a stick needs to be added to the drink.

  Scenario Outline: drink orders are translated into a specific protocol for the drink maker
    When we want to order a "<drink>"
    And we want to add "<number of sugars>" sugars
    Then the instructions understandable by the drink maker are "<instructions>"

  Examples:
    | drink     | number of sugars | instructions |
    | CHOCOLATE | 0                | H::          |
    | TEA       | 1                | T:1:0        |
    | COFFEE    | 2                | C:2:0        |

  Scenario Outline: if an order contains some sugars then a stick order needs to be added to the instructions
    When we want to add "<number of sugars>" sugars
    Then the instructions for the drink maker ends with ":0"
  Examples:
    | number of sugars |
    | 1                |
    | 2                |

  Scenario: if an order doesn't contains sugar then no stick order needs to be added to the instructions
    When we doesn't want to add sugar
    Then the instructions for the drink maker ends with ":"




