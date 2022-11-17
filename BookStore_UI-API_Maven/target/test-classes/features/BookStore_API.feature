@all @api
  Feature: Add book to the collection using API

    Scenario: Add book using API

      Given user access book store app using API
      When user sends POST request "https://demoqa.com/BookStore/v1/Books" to add book
      Then Status code should be 201 for new added book