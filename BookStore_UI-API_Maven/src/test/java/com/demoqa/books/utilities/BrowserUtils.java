package com.demoqa.books.utilities;


import com.demoqa.books.stepDefs.A_ScenarioName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtils {

    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            System.out.println("something happened in sleep method");

        }
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }


    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public static void setKeyAndValueWithScenarioNumber(String key, Object value) {
        String k = key + "_" + getScenarioNumber();
        ThreadStateHandler.setValue(k, value);
        UniqueArrayListForThread.getArray().add(k);//adds key in array to delete at the end of scenario in @After method in
        // Hooks
    }

    public static String getScenarioNumber() {
        String scenario = new A_ScenarioName().getScenario();
        // System.out.println("scenario = " + scenario);
        String[] s = scenario.split(" ");
        return s[0];
    }

    public static Object getValueOfScenarioNumAddedKey(String key) {
        A_ScenarioName scenarioName = new A_ScenarioName();
        String scenario = scenarioName.getScenario();
        String[] s = scenario.split(" ");
        return ThreadStateHandler.getValue(key + "_" + s[0]);
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


}
