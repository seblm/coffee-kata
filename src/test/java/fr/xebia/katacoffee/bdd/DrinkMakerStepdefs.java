package fr.xebia.katacoffee.bdd;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.xebia.katacoffee.DrinkType;
import fr.xebia.katacoffee.Logic;
import fr.xebia.katacoffee.Order;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class DrinkMakerStepdefs {

    private int numberOfSugars;
    private DrinkType drinkType;

    @When("^we want to order a \"([^\"]*)\"$")
    public void we_want_to_order_a(DrinkType drinkType) throws Throwable {
        this.drinkType = drinkType;
    }

    @And("^we want to add \"([^\"]*)\" sugars$")
    public void we_want_to_add_sugars(int numberOfSugars) throws Throwable {
        this.numberOfSugars = numberOfSugars;
    }

    @Then("^the instructions understandable by the drink maker are \"([^\"]*)\"$")
    public void the_instructions_understandable_by_the_drink_maker_are(String instructions) throws Throwable {
        Order order = new OrderBuilder().withDrinkType(drinkType).withNumberOfSugar(numberOfSugars).build();
        Assertions.assertThat(new Logic(order).push()).isEqualTo(instructions);
    }

    @Then("^the instructions for the drink maker ends with \"([^\"]*)\"$")
    public void the_instructions_for_the_drink_maker_ends_with(String instructions) throws Throwable {
        Order order = new OrderBuilder().withNumberOfSugar(numberOfSugars).build();
        assertThat(new Logic(order).push()).endsWith(instructions);
    }

    @When("^we doesn't want to add sugar$")
    public void we_doesn_t_want_to_add_sugar() throws Throwable {
        we_want_to_add_sugars(0);
    }

}
