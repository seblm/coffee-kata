package fr.xebia.katacoffee;


import org.junit.Before;
import org.junit.Test;

import fr.xebia.katacoffee.DrinkType;
import fr.xebia.katacoffee.Logic;
import fr.xebia.katacoffee.Order;

import static org.assertj.core.api.Assertions.assertThat;


public class LogicProtocolTest {

    private Logic logic ;

    @Before
    public void init(){

    }
    @Test
    public void should_push_chocolate_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.CHOCOLATE, 0, "2.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("H");
    }
    @Test
    public void should_push_tea_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.TEA, 0, "2.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("T");
    }
    @Test
    public void should_push_coffee_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 0, "1.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("C");
    }
    @Test
    public void should_push_1_sugar_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 1, "1.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C:1:0");
    }
    @Test
    public void should_push_2_sugars_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 2, "1.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C:2:0");
    }
    @Test
    public void should_push_no_sugar_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 0, "1.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C::");
    }

    @Test
    public void should_push_coffee_msg_only_if_money_is_enough() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 0, "0.6", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C::");

        logic = new Logic(new Order(DrinkType.COFFEE, 0, "0.5", ""));
        protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("M:");
        assertThat(protocolMsg).contains("0.1");
    }
    @Test
    public void should_push_tea_msg_only_if_money_is_enough() throws Exception {
        logic = new Logic(new Order(DrinkType.TEA, 0, "0.4", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("T::");

        logic = new Logic(new Order(DrinkType.TEA, 0, "0.2", ""));
        protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("M:");
        assertThat(protocolMsg).contains("0.2");
    }
    @Test
    public void should_push_chocolate_msg_only_if_money_is_enough() throws Exception {
        logic = new Logic(new Order(DrinkType.CHOCOLATE, 0, "0.5", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("H::");

        logic = new Logic(new Order(DrinkType.CHOCOLATE, 0, "0.1", ""));
        protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("M:");
        assertThat(protocolMsg).contains("0.4");
    }

    @Test
    public void should_push_orange_juice_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.ORANGE, 0, "2.0", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("O");
    }
    @Test
    public void should_push_orange_juice_msg_only_if_money_is_enough() throws Exception {
        logic = new Logic(new Order(DrinkType.ORANGE, 0, "0.1", ""));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("M:");
        assertThat(protocolMsg).contains("0.5");
    }

    @Test
    public void should_push_hot_coffee_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 0, "3.0", "hot"));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("Ch");
    }
    @Test
    public void should_push_hot_chocolate_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.CHOCOLATE, 0, "3.0", "hot"));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("Hh");
    }

    @Test
    public void should_push_hot_tea_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.TEA, 0, "3.0", "hot"));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("Th");
    }


    @Test
    public void can_print_amount_money() throws Exception {
        logic = new Logic(new Order(DrinkType.CHOCOLATE, 0, "3.0", "hot"));
        logic.add(new Order(DrinkType.CHOCOLATE, 0, "3.0", "hot"));
        logic.add(new Order(DrinkType.TEA, 0, "3.0", "hot"));

        String printMsg = logic.printAmountMoney();
        assertThat(printMsg).isEqualTo("1.4");

        logic.add(new Order(DrinkType.COFFEE, 0, "3.0", "hot"));

        printMsg = logic.printAmountMoney();
        assertThat(printMsg).isEqualTo("2.0");
    }

}
