@SampleFeatureUI @samplesearch @web
Feature: Display Related Items in the search results page
  As a User
  I should be able to search for an item
  So that I can view related items in the search results page

	@config
  Scenario: View related search results for keyword
    Given I am in the website homepage
    When I searched for an item
    Then I should see relevant items displayed
