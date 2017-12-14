@SampleFeatureUI @samplesearch @web @config
Feature: Display Related Items in the search results page
  As a User
  I should be able to search for an item
  So that I can view related items in the search results page

  Scenario: View related search results for keyword
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
    
   Scenario: Verify User is able to search for a product
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
    
   Scenario: Verify System shows an error message if a product is not found
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed