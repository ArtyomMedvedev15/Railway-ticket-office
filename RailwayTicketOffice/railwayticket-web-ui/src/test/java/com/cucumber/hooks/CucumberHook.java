package com.cucumber.hooks;

import com.cucumber.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHook {
    @Before
    public void setupDriver(){
        DriverManager.SetUpDriver();
    }

    @After
    public void closeDriver(){
        DriverManager.closeDriver();
    }
}
