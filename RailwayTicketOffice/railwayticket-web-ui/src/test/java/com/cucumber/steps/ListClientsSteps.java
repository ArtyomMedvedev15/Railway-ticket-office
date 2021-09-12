package com.cucumber.steps;

import com.cucumber.pages.ListClientsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class ListClientsSteps {
    private final ListClientsPage listClientsPage = new ListClientsPage();

    @Given("open web site, and open list clients page")
    public void openWebSiteAndOpenListClientsPage() {
        listClientsPage.OpenListClientPage();
    }

    @When("the user clicks on edit clients, and fill name on {string}, name edited")
    public void UserClicksOnEditClientsAndFillNameOnNameEdited(String name_client_edit) {
        Assertions.assertThat(listClientsPage.EditNameClient(name_client_edit)).
                overridingErrorMessage("Name client was not edited").isTrue();;
    }

    @When("the user clicks on edit clients, id client equals {string}")
    public void UserClicksOnEditClientsIdClientEquals(String id_client) {
        Assertions.assertThat(listClientsPage.VerifyIdEditClient(id_client)).
                overridingErrorMessage("Id edited client not equals %s",id_client).isTrue();;
        listClientsPage.OpenListClientPage();
    }

    @Then("the user clicks on link all clients, title page equals {string}")
    public void UserClicksOnLinkAllClientsTitlePageEquals(String title_page) {
        Assertions.assertThat(listClientsPage.VerifyTitlePageClient(title_page)).
                overridingErrorMessage("Title page not equal %s",title_page).isTrue();;
    }

    @Then("the user fill input with name {string}, and click find, get all clients with name")
    public void UserFillInputWithNameAndClickFindGetAllClientsWithName(String name_find_client) {
        Assertions.assertThat(listClientsPage.FindClientByName(name_find_client).size()>0).
                overridingErrorMessage("Count of client not bigger than 1").isTrue();;
    }
}
