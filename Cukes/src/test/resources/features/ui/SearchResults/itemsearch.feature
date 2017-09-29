@SearchResults @itemsearch @web
Feature: Display Related Items in the search results page
  As a User
  I should be able to search for an item
  So that I can view related items in the search results page

	@onescenario
  Scenario: View related search results for keyword
    Given I am on the homepage
    When I searched for item 'dress'
    Then I should see search results related to the searched keyword
      """
      Searh results contains 'dress' in the page
      """
