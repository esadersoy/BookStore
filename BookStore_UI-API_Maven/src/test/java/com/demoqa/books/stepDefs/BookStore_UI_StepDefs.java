package com.demoqa.books.stepDefs;

import com.demoqa.books.pages.BookStorePage;
import com.demoqa.books.utilities.BrowserUtils;
import com.demoqa.books.utilities.ConfigurationReader;
import com.demoqa.books.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.input.BrokenInputStream;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookStore_UI_StepDefs {
    Logger logger = LoggerFactory.getLogger(BookStore_UI_StepDefs.class);
    BookStorePage bsp = new BookStorePage();
    SoftAssertions softAssertions = new SoftAssertions();


    @Given("user lands on the login page")
    public void user_lands_on_the_login_page() {
        Driver.getDriver().get("https://demoqa.com/login");
        logger.info("User landed on https://demoqa.com/login ");
    }


    @When("user enters username and password")
    public void user_enters_username_and_password() {
        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        bsp.usernameInput.sendKeys(username);
        bsp.passwordInput.sendKeys(password);
        logger.info("User entered username and password ");

    }

    @When("user clicks {string} button")
    public void user_clicks_button(String buttonName) {
        WebElement buttonWithText = bsp.getButtonWithText(buttonName);
        BrowserUtils.scrollToElement(buttonWithText);
        BrowserUtils.clickWithJS(buttonWithText);
        logger.info("User clicked on {} button ", buttonName);

    }

    @When("user clicks on the title {string}")
    public void user_clicks_on_the_title(String name) {
        WebElement webElement = bsp.selectBookWithName(name);
        BrowserUtils.scrollToElement(webElement);
        webElement.click();
        logger.info("User clicked on the book --> {} ", name);
        BrowserUtils.setKeyAndValueWithScenarioNumber("bookName", name);


    }

    @When("user navigates to {string} page")
    public void user_navigates_to_page(String tab) {
        WebElement tabFromMenu = bsp.getTabFromMenu(tab);
        BrowserUtils.scrollToElement(tabFromMenu);
        BrowserUtils.clickWithJS(tabFromMenu);
        logger.info("User clicked on {} page", tab);
    }

    @When("user clicks on delete button of {string}")
    public void user_clicks_on_delete_button_of(String name) {
        BrowserUtils.clickWithJS(bsp.deleteButtonWithBookName(name));
        logger.info("User clicked delete button of {}", name);
    }


    @Then("user should be informed with {string} message on popup")
    public void user_should_be_informed_with_message_on_popup(String expectedMessage) {
        BrowserUtils.sleep(1);
        String actualText = Driver.getDriver().switchTo().alert().getText();
        softAssertions.assertThat(actualText).as("Warning message was NOT verified!").isEqualTo(expectedMessage);
        logger.info("Warning message was verified as -> {}", expectedMessage);
        softAssertions.assertAll();

    }

    @Then("selected book should be removed from collection")
    public void selected_book_should_be_removed_from_collection() {
        String expectedName = (String) BrowserUtils.getValueOfScenarioNumAddedKey("bookName");
        List<WebElement> booksInCollection = bsp.booksInCollection;

        if (booksInCollection.size() > 0) {
            List<String> bookNames = BrowserUtils.getElementsText(booksInCollection);
            softAssertions.assertThat(bookNames.contains(expectedName)).as(expectedName + " is STILL in collection ->" + bookNames).isFalse();
            logger.info("Verified that {} was removed from collection", expectedName);
        } else {
            logger.info("There is NO book in collection");
        }
        softAssertions.assertAll();
    }

    @Then("user should land on {string} page")
    public void user_should_land_on_page(String expectedHeader) {
        BrowserUtils.sleep(2);
        String actualHeader = bsp.pageMainHeader.getText();
        softAssertions.assertThat(actualHeader).as("Actual header is not verified!").isEqualTo(expectedHeader);
        logger.info("Actual header was verified as --> {}", actualHeader);
    }


    @Then("selected book information should be displayed")
    public void selected_book_information_should_be_displayed() {
        String expectedName = (String) BrowserUtils.getValueOfScenarioNumAddedKey("bookName");
        String actualName = bsp.bookTitleOnDetailPage.getText();
        softAssertions.assertThat(actualName).as("Book title is not verified!").isEqualTo(expectedName);
        logger.info("Book title was verified as --> {}", actualName);
    }

    @Then("selected book should be displayed in collection")
    public void selected_book_should_be_displayed_in_collection() {
        String expectedName = (String) BrowserUtils.getValueOfScenarioNumAddedKey("bookName");
        List<WebElement> booksInCollection = bsp.booksInCollection;
        if (booksInCollection.size() > 0) {
            List<String> bookNames = BrowserUtils.getElementsText(booksInCollection);
            softAssertions.assertThat(bookNames.contains(expectedName)).as(bookNames + " do not contain " + expectedName).isTrue();
            logger.info("Verified that {} is in collection", expectedName);
        } else {
            logger.info("There is NO book in collection");
        }

        // In order not to cause exceptions added book was deleted intentionally
        user_clicks_on_delete_button_of(expectedName);
        user_clicks_button("OK");
        softAssertions.assertAll();
    }

    @And("user accepts alert message")
    public void userAcceptsAlertMessage() {
        try {
            BrowserUtils.sleep(1);
            Driver.getDriver().switchTo().alert().accept();
        } catch (UnhandledAlertException e) {

            e.printStackTrace();
        }

    }


}