Feature: Creating a supporter account

  Scenario Outline:
    Given I am using <browser>
    And I have filled in the fields partOne <dateOfBirth>, <name>, <lastName>, email and confirmEmail
    And I have filled in the fields partTwo <password> and <confirmPassword>
    And I have checked checkboxes <termsConditions>, <age> and <ethicsConduct>
    When I click on the confirm button
    Then I get the <result> account is created/not created
    Examples:
      | browser | dateOfBirth | name      | lastName  | password   | confirmPassword | termsConditions | age     | ethicsConduct | result                                                                    |
      | chrome  | 22/08/1989  | Alexander | Eriksson  | lex987@Er  | lex987@Er       | checked         | checked | checked       | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 |
      | firefox | 12/05/1982  | Ilsa      | Magnusson | Smith82@Il | Smith82@Il      | not checked     | checked | checked       | You must confirm that you have read and accepted our Terms and Conditions |
      | chrome  | 11/07/1981  | Jessica   |           | Lks587@11  | Lks587@11       | checked         | checked | checked       | Last Name is required                                                     |
      | firefox | 25/01/1959  | Irina     | Carlsson  | ir@134     | aaakih12        | checked         | checked | checked       | Password did not match                                                    |











