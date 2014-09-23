Feature: Take a picture

  A photomaton let's a user take a picture of himself. The picture can either be in color or in black and white.

  Scenario Outline: A user can take a portrait of himself and choose between color or n&b

    Given a user wanted a "<colorimetry>" portrait of himself
    When the user take the picture
    Then the photomaton should print the "<colorimetry>" portrait of the user

  Examples:
    | colorimetry |
    | B&W         |
    | Color       |

  Scenario: A user can take a maximum of three pictures before validation. The last attempt is automatically sent to the photomaton to be processed.

    When a user take three pictures in a row
    Then the photomaton should print the last one

  Scenario: A user can take a picture if the correct amount of money is given to the photomaton

    When a user give enough money to the photomaton
    Then he can take a picture