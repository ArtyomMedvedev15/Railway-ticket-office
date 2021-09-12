package com.cucumber.steps;

import com.cucumber.pages.ListTrainPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class ListTrainsSteps {
    private final ListTrainPage listTrainPage = new ListTrainPage();

    @When("the user clicks on add new train button, form with title {string} appears")
    public void VerifyTitleAddForm(String title_form){
        Assertions.assertThat(listTrainPage.VerifyTitleAddForm(title_form)).
                overridingErrorMessage("Form with title '%s' is not displayed",title_form).isTrue();
    }

    @When("the user add new train, with all filed, new train appears")
    public void UserAddNewTrainWithAllFiledNewTrainAppears() {
        Assertions.assertThat(listTrainPage.AddNewTrain().size()>5).
        overridingErrorMessage("Count of trains not bigger than 5").isTrue();
    }

    @When("the user clicks on edit train, title train equals {string}")
    public void UserClicksOnEditTrainTitleTrainEquals(String idtrain) {
        listTrainPage.EditTrainTitle(idtrain);
        listTrainPage.OpenWebSite();
    }

    @Then("the user clicks on link all train, title page equals {string}")
    public void UserClicksOnLinkAllTrainTitlePageEquals(String title_page) {
        Assertions.assertThat(listTrainPage.VerifyTitlePage(title_page)).
                overridingErrorMessage("Page with title '%s' is not displayed",title_page).isTrue();
    }


    @Given("open web site, and open list train page")
    public void openWebSiteAndOpenListTrainPage() {
        listTrainPage.OpenWebSite();
    }
}
