Feature: Sharing a picture

  The photomaton offer the possibility for the user to share his taken picture by email. This feature concerns only the portait format.

  Scenario: Sharing by email a portrait

    Given a user has taken a portrait
    When he share it by email
    Then an email should be send with the taken portrait in it
