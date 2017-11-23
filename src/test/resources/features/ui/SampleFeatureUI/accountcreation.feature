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
