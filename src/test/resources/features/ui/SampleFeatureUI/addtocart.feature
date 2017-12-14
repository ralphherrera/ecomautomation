@SampleFeatureUI @samplesearch @web @config
Feature: Display Related Items in the search results page
  As a User
  I should be able to search for an item
  So that I can view related items in the search results page

  Scenario: Verify User is able to add items to cart
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
    
   Scenario: Verify User is able to remove items from cart
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
    
   Scenario: Verify User is able to update item quantity in cart
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
     
   Scenario: Verify User is able to check item details for items in cart
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
     
   Scenario: Verify User is able to apply promos in cart
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
    
