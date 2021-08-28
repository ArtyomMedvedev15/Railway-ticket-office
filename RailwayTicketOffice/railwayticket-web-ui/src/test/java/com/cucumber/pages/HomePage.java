package com.cucumber.pages;

import com.cucumber.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;

public class HomePage extends BasePage{

    private static final String RAILWAY_HOME_URL = "http://localhost:8080/";
    private static final String TEXT_PATTERN = "/html/body/section/div/div/div/div/form/div[1]/p";
    private static final String FILED_WITH_DATE = "/html/body/section/div/div/div/div/form/div[3]/div[1]/input";
    private static final String BLOCK_WITH_CHOOSE_DATE = "//*[@id=\"ui-datepicker-div\"]";
    private static final String FILED_WITH_STATIONS = "/html/body/section/div/div/div/div/form/div[3]/div[5]/select";
    private static final String BLOCK_WITH_CHOOSE_STATIONS = "/html/body/section/div/div/div/div/form/div[3]/div[7]/select/option[2]";

    public void OpenHomePage(){
        DriverManager.getDriver().get(RAILWAY_HOME_URL);
    }

    public WebElement DateFiledShow(){
        DriverManager.getDriver().findElement(new By.ByXPath(FILED_WITH_DATE)).click();
        return findElement(By.xpath(BLOCK_WITH_CHOOSE_DATE));
    }

    public boolean isPageWithTitleDisplayed(String title){
        return findElement(By.xpath(TEXT_PATTERN)).getText().equals(title);
    }

    public WebElement StationsFiledShow(){
        findElement(new By.ByXPath(FILED_WITH_STATIONS)).click();
        return findElement(By.xpath(BLOCK_WITH_CHOOSE_STATIONS));
    }

    public boolean isValidMessageDisplayed(){
        findElement(new By.ByXPath("/html/body/section/div/div/div/div/form/div[3]/div[9]/button")).click();
        return findElement(new By.ByXPath(FILED_WITH_DATE)).getAttribute("validationMessage").equals("Заполните это поле.");
    }

}
