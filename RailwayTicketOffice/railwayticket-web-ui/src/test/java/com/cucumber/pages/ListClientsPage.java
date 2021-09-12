package com.cucumber.pages;

import com.cucumber.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListClientsPage extends BasePage{

    private static final String INPUT_NAME_FIND_CLIENT = "/html/body/section/div[2]/div[1]/div/form/div/div[1]/input";
    private static final String BTN_FOR_FIND_CLIENT = "/html/body/section/div[2]/div[1]/div/form/div/div[2]/button";
    private static final String TITLE_PAGE_CLIENTS = "/html/body/section/div[1]/p";
    private static final String EDIT_CLIENT_BTN = "/html/body/section/div[3]/div/div/table/tbody/tr/td[7]/div/a";
    private static final String EDIT_ID_CLIENT = "/html/body/section/div/div[2]/div/div/div/form/div[1]/p/span";
    private static final String EDIT_SEND_BTN = "/html/body/section/div/div[2]/div/div/div/form/div[3]/div[7]/button";
    private static final String LINK_LIST_CLIENT = "/html/body/nav/div/div/ul/li[3]/a";
    private static final String LINK_WEBSITE = "http://localhost:8080";
    private static final String INPUT_EDIT_NAME_CLIENT = "/html/body/section/div/div[2]/div/div/div/form/div[3]/div[1]/input";

    public void OpenListClientPage(){
        DriverManager.getDriver().get(LINK_WEBSITE);
        findElement(new By.ByXPath(LINK_LIST_CLIENT)).click();
    }

    public boolean VerifyTitlePageClient(String title_page){
        return findElement(new By.ByXPath(TITLE_PAGE_CLIENTS)).getText().equals(title_page);
    }

    public List<WebElement> FindClientByName(String name_client){
        findElement(new By.ByXPath(INPUT_NAME_FIND_CLIENT)).sendKeys(name_client);
        findElement(new By.ByXPath(BTN_FOR_FIND_CLIENT)).click();
        return findElements("node");
    }

    public boolean VerifyIdEditClient(String id_client){
        findElement(new By.ByXPath(EDIT_CLIENT_BTN)).click();
        return findElement(new By.ByXPath(EDIT_ID_CLIENT)).getText().equals(id_client);
    }

    public boolean EditNameClient(String name_client){
        findElement(new By.ByXPath(EDIT_CLIENT_BTN)).click();
        findElement(new By.ByXPath(INPUT_EDIT_NAME_CLIENT)).clear();
        findElement(new By.ByXPath(INPUT_EDIT_NAME_CLIENT)).sendKeys(name_client);
        findElement(new By.ByXPath(EDIT_SEND_BTN)).click();
        DriverManager.getDriver().switchTo().alert().accept();
        return findElement(new By.ByXPath("/html/body/section/div[3]/div/div/table/tbody/tr/td[1]")).getText().equals(name_client);
    }


}
