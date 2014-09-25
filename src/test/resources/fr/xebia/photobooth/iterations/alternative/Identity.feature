Feature: Identity pictures

  A photomaton can't take identy pictures that respect state standards. When you choose this format you get four pictures in the end.

  Scenario: a user can't choose vintage format for identity pictures

    When a user choose vintage identity pictures
    Then the photomaton should inform the user that this choice is not available

  Scenario: a user can know if the taken picture respect the standard imposed by the identity format

    Given a user wanted identity pictures
    When the user take the picture
    And the picture respect the identity standard
    Then the photomaton should print the desired picture

  Scenario: if the taken picture do not respect the standard then the user can take another one

    Given a user wanted identity pictures
    When the user take the picture
    And the picture do not respect the identity standard
    Then the photomaton should print to the user the following message : "your picture doesn't respect the identity standard"
    And the photomaton should allow the user to take a new picture
