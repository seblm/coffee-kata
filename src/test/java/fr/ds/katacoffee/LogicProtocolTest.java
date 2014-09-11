package fr.ds.katacoffee;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogicProtocolTest {

    private Logic logic ;

    @Before
    public void init(){

    }

    @Test
    public void should_push_chocolate_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.CHOCOLATE, 0));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("H");
    }
    @Test
    public void should_push_tea_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.TEA, 0));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("T");
    }
    @Test
    public void should_push_coffee_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 0));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).startsWith("C");
    }
    @Test
    public void should_push_1_sugar_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 1));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C:1:0");
    }
    @Test
    public void should_push_2_sugars_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 2));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C:2:0");
    }
    @Test
    public void should_push_no_sugar_msg() throws Exception {
        logic = new Logic(new Order(DrinkType.COFFEE, 0));
        String protocolMsg = logic.push();
        assertThat(protocolMsg).isEqualTo("C::");
    }

}
