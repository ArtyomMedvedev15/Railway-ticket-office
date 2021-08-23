package com.cucumber.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;

public class DriverManager {

    private final static String CHROME_DRIVER_PATH = "resources/chromedriver.exe";
    private static final long IMPLICIT_WAIT_TIMEOUT = 5;
    private static final long PAGE_LOAD_TIMEOUT = 20;
    private static WebDriver chromeDriver;

    public DriverManager() {
    }

    public static void SetUpDriver() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return chromeDriver;
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(WebDriver::quit);
    }
}
