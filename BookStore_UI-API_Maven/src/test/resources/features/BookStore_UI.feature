@all

Feature: Add Book / Remove Book


  Scenario: Add book to collection
    Given user lands on the login page
    When user enters username and password
    And user clicks "Login" button
    Then user should land on "Profile" page
    When user clicks "Go To Book Store" button
    And user clicks on the title "Git Pocket Guide"
    Then selected book information should be displayed
    When user clicks "Add To Your Collection" button
    And user accepts alert message
    And user navigates to "Profile" page
    Then selected book should be displayed in collection

  Scenario: Delete a book from collection
    Given user lands on the login page
    When user enters username and password
    And user clicks "Login" button
    And user clicks "Go To Book Store" button
    And user clicks on the title "Git Pocket Guide"
    And user clicks "Add To Your Collection" button
    And user accepts alert message
    And user navigates to "Profile" page
    When user clicks on delete button of "Git Pocket Guide"
    And user clicks "OK" button
    Then user should be informed with "Book deleted." message on popup
    And user accepts alert message
    And user navigates to "Profile" page
    Then selected book should be removed from collection
#
