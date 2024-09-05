@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: error validation loginpage
  
    Given I landed on Ecommerce Page
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
     | name  								|  password		        |
     | infovel@gmail.com    |  !228Vishnuarcotva@ |
