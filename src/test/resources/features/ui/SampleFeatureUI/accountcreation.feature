@SampleFeatureUI @accountcreation @web
Feature: Account Creation
  As a User
  I should be able to create an account
  So that I can shop maximize the shop's features and shop effortlessly

@config
  Scenario: User Registration
    Given I am in the website homepage
    And I navigate on Account Creation Page
    When I fill up the account creation form
    #And I submit the account creation form
    #Then I should see the account is logged in

  Scenario: Verify User is able to successfully create an account
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
     
  Scenario: Verify User is unable to create an account when email used is already taken
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
     
  Scenario: Verify User is unable to create an account when a required field is left blank
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
     
  Scenario: Verify User receives an email after successful registration
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
     
  Scenario: Verify User is able to login after successfully creating an account
     Given I am in the website homepage
     When I searched for an item
     Then I should see relevant items displayed
