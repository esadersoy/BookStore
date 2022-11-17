package com.demoqa.books.services;

import com.demoqa.books.utilities.BrowserUtils;
import com.demoqa.books.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authorized {
    Response response;


    public void authorized() {
        String userName = (String) BrowserUtils.getValueOfScenarioNumAddedKey("userName");
        String password = ConfigurationReader.getProperty("commanPassword");

        Map<String, Object> body = new HashMap<>();

        body.put("userName", userName);
        body.put("password", password);

        response = given().contentType(ContentType.JSON).
                body(body).log().all().
                with().post("https://demoqa.com/Account/v1/Authorized").prettyPeek();


    }

    public void verifyStatusCode() {

        response.then().assertThat().statusCode(200);
    }
}
