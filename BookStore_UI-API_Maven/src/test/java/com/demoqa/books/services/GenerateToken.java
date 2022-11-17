package com.demoqa.books.services;

import com.demoqa.books.utilities.BrowserUtils;
import com.demoqa.books.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GenerateToken {
    Response response;
    public String token;

    static Register register = new Register();

    public void generateToken() {

        String  userName = (String) BrowserUtils.getValueOfScenarioNumAddedKey("userName");

        String body = "{\n" +
                "  \"userName\": \"" + userName+ "\",\n" +
                "  \"password\": \"" + ConfigurationReader.getProperty("commanPassword") + "\"\n" +
                "}";

        response = given().contentType(ContentType.JSON).
                body(body).log().all().
                with().post("https://demoqa.com/Account/v1/GenerateToken").prettyPeek();

        token = response.jsonPath().getString("token");
        BrowserUtils.setKeyAndValueWithScenarioNumber("token", token);

    }

    public void verifyUser() {

        response.then().assertThat().statusCode(200);
    }
}
