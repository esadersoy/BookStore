package com.demoqa.books.pages;

import com.demoqa.books.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "input#userName")
    public WebElement usernameInput;

    @FindBy(css = "input#password")
    public WebElement passwordInput;


    @FindBy(xpath = "//label[@id='title-label']/../following-sibling::div/label")
    public WebElement bookTitleOnDetailPage;

    public WebElement selectBookWithName(String bookName) {
        return Driver.getDriver().findElement(By.xpath("//a[contains(text(),'"+ bookName + "')]"));
    }


    public WebElement getTabFromMenu(String tab) {
        return Driver.getDriver().findElement(By.xpath("//li//span[text()='" + tab + "']"));
    }

    public WebElement deleteButtonWithBookName(String name) {
        return Driver.getDriver().findElement(By.xpath("//a[contains(text(),'"+ name +"')]/../../../following-sibling::div[3]/div/span[@id='delete-record-undefined']"));
    }
    @FindBy(css = "div.main-header")
    public WebElement pageMainHeader;

    public WebElement getButtonWithText(String buttonName) {
        return Driver.getDriver().findElement(By.xpath("//button[text()='" + buttonName + "']"));

    }

    @FindBy(xpath = "//a[contains(@href,'/profile?book=')]")
    public List<WebElement> booksInCollection;


}
