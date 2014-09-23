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

