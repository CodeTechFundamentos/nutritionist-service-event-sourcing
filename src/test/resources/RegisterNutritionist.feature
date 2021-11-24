Feature: Register a new Nutritionist

  Scenario Outline: As a nutritionist i want to sign up with my cnp code.

    Given I can sign up as a nutritionist
    And I sending register form to be created with my cnp code<cnp>
    Then I should be able to see my newly created account
    Examples:
      | cnp |
      | 123 |

  Scenario Outline: As a nutritionist i want to sign up without my cnp code.

    Given I can sign up as a nutritionist
    And I sending register form to be created without cnp code<cnp>
    Then I should be able to see an error message<message>
    Examples:
      | cnp | message                   |
      |   0 | "Ingrese un codigo valido"|