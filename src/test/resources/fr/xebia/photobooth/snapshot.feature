Feature: snapshot

  Scenario: should takes a snapshot
    Given I go to homepage
    When I click on snapshot button
    Then My picture should be displayed
