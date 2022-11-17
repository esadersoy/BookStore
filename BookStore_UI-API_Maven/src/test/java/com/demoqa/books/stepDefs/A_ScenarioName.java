package com.demoqa.books.stepDefs;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.HashMap;

public class A_ScenarioName {
    private static HashMap<Integer,String> scenarios;

    public A_ScenarioName(){ //or even inside of your singleton's getInstance();
        if(scenarios == null)
            scenarios = new HashMap<Integer,String>();
    }

    @Before
    public void beforeHook(Scenario scenario) {
        addScenario(scenario.getName());
        System.out.println();
//        System.out.println("**Scenario starts**! ->> " + getScenario()+" ");
//        String currentScenario = getScenario();
//        String[] s = currentScenario.split(" ");
//        String key = "scenario_" + s[0];
        //BrowserUtilities.setKeyAndValueWithScenarioNumber("scenario", "scenario1");
        //BrowserUtilities.setKeyAndValueWithScenarioNumber("scenario_"+s[0],key);
    }

    private void addScenario(String scenario){
        Thread currentThread = Thread.currentThread();
        int threadID = currentThread.hashCode();
        scenarios.put(threadID,scenario);
    }

    public synchronized String getScenario(){
        Thread currentThread = Thread.currentThread();
        int threadID = currentThread.hashCode();
        //System.out.println("threadID = " + threadID);
        return scenarios.get(threadID);
    }

}
