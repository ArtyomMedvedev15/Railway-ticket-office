package com.cucumber.pages;

import com.cucumber.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ListTrainPage extends BasePage{

    private static final String TITLE_PAGE = "/html/body/section/div[1]/p";
    private static final String BTN_FOR_ADD = "/html/body/section/div[2]/div[1]/a";
    private static final String TITLE_FORM_ADD = "/html/body/div[1]/div[2]/div/div/div/div/form/div[1]";
    private static final String INPUT_NAME_TRAIN = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[1]/input";
    private static final String SELECT_DEPARTURE_STATION = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[3]/select";
    private static final String SELECT_ARRIVAL_STATION = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[5]/select";
    private static final String SELECT_TYPE_TRAIN = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[7]/select";
    private static final String INPUT_DATE_DEPARTURE = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[9]/input";
    private static final String INPUT_DATE_ARRIVAL = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[11]/input";
    private static final String INPUT_TOTAL_TICKET = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[13]/input";
    private static final String INPUT_AVAILABLE_TICKET = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[15]/input";
    private static final String INPUT_PRICE_TICKET = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[17]/input";
    private static final String BTN_FOR_EDIT = "/html/body/section/div[3]/div/div/table/tbody/tr[1]/td[11]/div/a";
    private static final String ID_EDIT_TRAIN = "/html/body/section/div/div/div/div/div/form/div[1]/p[2]/span";
    private static final String BTN_SEND_ADD = "/html/body/div[1]/div[2]/div/div/div/div/form/div[3]/div[19]/button";

    public void OpenWebSite(){
        DriverManager.getDriver().get("http://localhost:8080/");
        findElement(new By.ByXPath("/html/body/nav/div/div/ul/li[2]/a")).click();
    }

    public boolean VerifyTitleAddForm(String title_form){
        findElement(new By.ByXPath(BTN_FOR_ADD)).click();
        return findElement(new By.ByXPath(TITLE_FORM_ADD)).getText().equals(title_form);
    }

    public boolean VerifyTitlePage(String title_page){
        return findElement(new By.ByXPath(TITLE_PAGE)).getText().equals(title_page);
    }

    public List<WebElement> AddNewTrain(){
        findElement(new By.ByXPath(INPUT_NAME_TRAIN)).sendKeys("TestAddTrain"+new Random().nextInt(10000));
        findElement(new By.ByXPath(SELECT_DEPARTURE_STATION)).sendKeys("BREST");
        findElement(new By.ByXPath(SELECT_ARRIVAL_STATION)).sendKeys("MINSK");
        findElement(new By.ByXPath(SELECT_TYPE_TRAIN)).sendKeys("ECONOM");
        findElement(new By.ByXPath(INPUT_DATE_DEPARTURE)).sendKeys("2021-08-26 00:00");
        findElement(new By.ByXPath(INPUT_DATE_ARRIVAL)).sendKeys("2021-08-28 03:11");
        findElement(new By.ByXPath(INPUT_TOTAL_TICKET)).sendKeys("123");
        findElement(new By.ByXPath(INPUT_AVAILABLE_TICKET)).sendKeys("123");
        findElement(new By.ByXPath(INPUT_PRICE_TICKET)).sendKeys("123");
        findElement(new By.ByXPath(BTN_SEND_ADD)).click();
        DriverManager.getDriver().switchTo().alert().accept();
        return findElements("node");
    }

    public boolean EditTrainTitle(String id_train){
        findElement(new By.ByXPath(BTN_FOR_EDIT)).click();
        return findElement(new By.ByXPath(ID_EDIT_TRAIN)).getText().equals(id_train);
    }

}
