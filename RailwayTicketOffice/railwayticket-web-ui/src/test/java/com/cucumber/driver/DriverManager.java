package com.cucumber.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private final static String CHROME_DRIVER_PATH = "src/test/resources/chromedriver.exe";
    private static final long IMPLICIT_WAIT_TIMEOUT = 5;
    private static final long PAGE_LOAD_TIMEOUT = 20;

    private static final ThreadLocal<WebDriver>chromeDriverLocal = new ThreadLocal<>();
    public DriverManager() {
    }

    public static void SetUpDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        chromeDriverLocal.set(chromeDriver);
    }

    public static WebDriver getDriver() {
        return chromeDriverLocal.get();
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(WebDriver::quit);
    }
}
