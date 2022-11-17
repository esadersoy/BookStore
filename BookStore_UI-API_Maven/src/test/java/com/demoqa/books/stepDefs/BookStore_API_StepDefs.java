package com.demoqa.books.stepDefs;

import com.demoqa.books.services.AddBook;
import com.demoqa.books.services.Authorized;
import com.demoqa.books.services.GenerateToken;
import com.demoqa.books.services.Register;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookStore_API_StepDefs {
    Register register = new Register();
    GenerateToken generateToken = new GenerateToken();
    Authorized authorized = new Authorized();
    AddBook addBook = new AddBook();


    @Given("user access book store app using API")
    public void userAccessBookStoreAppUsingAPI() {

        register.userRegister();
        register.verifyUser();

        generateToken.generateToken();
        generateToken.verifyUser();

        authorized.authorized();
        authorized.verifyStatusCode();

    }

    @When("user sends POST request {string} to add book")
    public void userSendsPOSTRequestToAddBook(String address) {
        addBook.addbook(address);


    }

    @Then("Status code should be {int} for new added book")
    public void statusCodeShouldBeForNewAddedBook(int statusCode) {

        addBook.verifyAddBook(statusCode);
    }
}
