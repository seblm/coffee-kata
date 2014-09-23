Feature: Picture Processor Protocol

  The photomaton embed a picture processor that is responsible for processing the pictures taken by users. In order to communicate with it, a specific protocol needs to be used.

  This protocol follows the format : "colorimetry of the picture";"format of the picture"

  Scenario: picture orders are translated into a specific protocol for the picture processor
    When we want a color portrait picture
    Then the instructions understandable by the picture processor should be "C:P"





