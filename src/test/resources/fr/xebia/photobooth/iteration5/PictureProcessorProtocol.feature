Feature: Picture Processor Protocol

  The photomaton embed a picture processor that is responsible for processing the pictures taken by users. In order to communicate with it, a specific protocol needs to be used.

  This protocol follows the format : "colorimetry of the picture";"format of the picture"

  Scenario Outline: picture orders are translated into a specific protocol for the picture processor
    When we want a "<colorimetry>" "<format>" picture
    Then the instructions understandable by the picture processor should be "<picture processor instructions>"

  Examples:
    | colorimetry     | format   | picture processor instructions |
    | Color           | Identity | C;P                            |
    | Black And White | Portrait | BW;P                           |
    | Color           | Identity | C;I                            |
    | Vintage         | Mini     | V;M                            |

