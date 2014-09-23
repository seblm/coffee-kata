Feature: Take a picture

  A photomaton let's a user take a picture of himself.

  Scenario: A user can take a full color portrait of himself using the photomaton

    Given a user wanted a full color portrait of himself
    When the user take the picture
    Then the photomaton should print the full color portrait of the user

