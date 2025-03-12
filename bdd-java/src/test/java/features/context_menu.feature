Feature: Heroku Challenge

  Scenario: Right Click on the Context App
    Given I am on the heroku app context menu page
    When I click on the box
    Then I should see the js alert.
    And I should see {You selected a context menu} text in the alert