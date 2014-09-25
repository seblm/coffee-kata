Feature: Prices of picture

  In order to let a user take a picture, the correct amount of money is given to the photomaton.

  Scenario Outline: Each type of picture has a a different price that needs to be paid in order to be taken

    Given the price of a "<colorimetry>" "<format>" is "<picture price>" euros
    When "<amount of money>" euros is given to the photomaton
    Then the photomaton should allow the photo taking

  Examples:
    | colorimetry | format   | picture price | amount of money |
    | Color       | Portrait | 1             | 3               |
    | B&W         | Portrait | 3             | 3               |
    | Color       | Identity | 3             | 4               |
    | Vintage     | Portrait | 1,5           | 4               |
    | Vintage     | Mini     | 4,5           | 6               |
    | B&W         | Mini     | 4             | 4.5             |
    | Color       | Mini     | 4             | 4               |

  Scenario: If the amount of money given to the photomaton is not enough comparing to the picture price, then the picture can't be taken

    Given the price of a picture is 5 euros
    When 2 euros is given to the photomaton
    Then the photomaton should not allow the photo taking
