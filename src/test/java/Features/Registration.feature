Feature: User Registration on Basketball England Website

  Scenario Outline: Create a new user account

        Given initializeDriver "<Browser>"
        And   user on the registration page
        And   user enter "<Date of birth> "
        When  user enter name "<First name>" and "<Last name>"
        Then  user enter email "<Email>" and "<Confirm email>"
        Then  user  enter password "<Password>" and "<Retype password>"
        And   user click on the your role checkbox
        And   user click on the Account Conformation checkbox
        And   user click on COMMUNICATION PREFERENCES checkbox
        And   user click on ETHICS AND CONDUCT checkbox
        Then   user click on Confirm and join button

        Examples:
              | Browser | Date of birth | First name | Last name | Email | Confirm email | Password | Retype password |
              | Chrome  | 03/09/1986    | Shahreen   | Jahan     | lopacuty@hotmail.com | lopacuty@hotmail.com | Lopa_123 | Lopa_123        |
              | IE | 01/01/1986    | RubelRubelRubel... | Rahman | Rubelweb@hotmail.com | Rubelweb@hotmail.com | Rubel_123 | Rubel_123 |
              | Chrome  | 03/09/1986    | Shahreen   | Jahan     | lopacuty@hotmail.com | lopacuty@hotmail.com | Lopa_123 | Lopa_123        |
              | IE | 03/09/1986    | Shahreen   | Jahan     |         | test@gmail.com        | Test_123 | Test_123        |