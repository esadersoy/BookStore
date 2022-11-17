package com.demoqa.books.services;

import com.demoqa.books.utilities.BrowserUtils;
import com.demoqa.books.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.Map;
import java.util.TreeMap;


public class Register {

    Faker faker = new Faker();
    Response response;
    public String userName;
    public String id;
    public String userID;


    public void userRegister() {

        String password = ConfigurationReader.getProperty("commanPassword");

        userName = faker.name().firstName();
        BrowserUtils.setKeyAndValueWithScenarioNumber("userName",userName);

        Map<String, Object> body = new TreeMap<>();
        body.put("userName", userName);
        body.put("password", password);

        response = RestAssured.given().contentType(ContentType.JSON).
                body(body).log().all().
                when().post("https://demoqa.com/Account/v1/User").prettyPeek();

        userID = response.jsonPath().get("userID");
        BrowserUtils.setKeyAndValueWithScenarioNumber("userID",userID);
    }

    public void verifyUser() {

        response.then().assertThat().statusCode(201);
    }
}
