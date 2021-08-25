package com.cucumber.steps;

import com.cucumber.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class Homepage {

    private final HomePage homePage = new HomePage();

    @Given("the user opens website Railway ticket office")
    public void OpensWebsiteRailwayTicketOffice() {
        homePage.OpenHomePage();
    }

    @When("the user clicks on the filed with date, a date field appears")
    public void CheckFiledWithDateADateFieldAppears() {
        homePage.DateFiledShow();
    }

    @When("the user clicks on the filed with station, a list appears")
    public void CheckFiledWithStationAListAppears() {
        homePage.StationsFiledShow();
    }

    @Then("form with title {string} is displayed")
    public void DisplayedTitleWithFindFormDisplayed(String formtitle) {
        Assertions.assertThat(homePage.isPageWithTitleDisplayed(formtitle)).
                overridingErrorMessage("Page with title '%s' is not displayed",formtitle).isTrue();
    }

    @When("try to send request with empty field, valid message appears")
    public void tryToSendRequestWithEmptyFieldValidMessageAppears() {
        Assertions.assertThat(homePage.isValidMessageDisplayed()).
                overridingErrorMessage("Validation message for input date doesn't appear").isTrue();
    }
}
