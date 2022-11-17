package com.demoqa.books.services;

import com.demoqa.books.utilities.BrowserUtils;
import com.demoqa.books.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddBook {

    Response response;

    public void addbook(String address) {

        String userID = (String) BrowserUtils.getValueOfScenarioNumAddedKey("userID");
        String token = (String) BrowserUtils.getValueOfScenarioNumAddedKey("token");

        String body = "{\n" +
                "  \"userId\": \"" + userID + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"" + ConfigurationReader.getProperty("isbn4") + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Map<String, Object> bearerToken = new HashMap<>();

        bearerToken.put("Authorization", "Bearer " + token);


        response = given().contentType(ContentType.JSON).headers(bearerToken).
                body(body).log().all().with().post(address);


    }

    public void verifyAddBook(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
}
