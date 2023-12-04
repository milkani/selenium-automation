package lv.acodemy.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lv.acodemy.page_object.sauce_pages.InventoryPage;
import lv.acodemy.page_object.sauce_pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static lv.acodemy.utils.LocalDriverManager.getInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class AuthSteps {

    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();
    WebDriverWait wait = new WebDriverWait(getInstance(), Duration.ofSeconds(5));

    @When("user logs in with credentials: {string} and {string}")
    public void logIn(String username, String password) {
        loginPage.authorize(username, password);
    }
    @Then("user is authenticated")
    public void checkIfAuthenticated() {
        wait.until(visibilityOfAllElements(inventoryPage.getInventoryItems()));
        assertThat(inventoryPage.getInventoryItems()).hasSize(6);
    }

    @Then("user see error message: {string}")
    public void userSeeErrorMessage(String errorMessage) {
        assertThat(loginPage.getErrorMessage().getText()).isEqualTo(errorMessage);
    }
}
