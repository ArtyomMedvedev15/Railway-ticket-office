package com.cucumber.hooks;

import com.cucumber.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHook {
    private static final String MEDIA_TYPE = "image/png";

    @After
    public void takeScreenshot(Scenario scenario){
        scenario.write(DriverManager.getDriver().getCurrentUrl());
        byte[]screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot,MEDIA_TYPE,scenario.getName());
    }
}
