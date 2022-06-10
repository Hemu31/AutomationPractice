@test
Feature: Automation Practice Test for HMRC

  Scenario: Add costly dress to cart
    Given I open the automation practice site
    Then I assert the page title to be "My Store"
    When I click on the Dresses tab
    Then I assert the page title to be "Dresses - My Store"
    When I add costly dress to cart
    And I click on proceed to checkout
    Then I assert the page title to be "Order - My Store"
    And I validate the price in the order summary page
