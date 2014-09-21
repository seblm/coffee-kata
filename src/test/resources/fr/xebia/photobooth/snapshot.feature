Feature: snapshot

  Scenario: should takes a snapshot
    Given I go to homepage
    Given I fill video url
    Given I click on start Video
    And video is displayed
    When I click on snapshot button
    Then My picture should be displayed
