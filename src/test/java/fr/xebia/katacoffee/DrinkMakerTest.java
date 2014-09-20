package fr.xebia.katacoffee;


import org.junit.Test;

import fr.xebia.katacoffee.DrinkMaker;
import fr.xebia.katacoffee.DrinkType;
import fr.xebia.katacoffee.Logic;
import fr.xebia.katacoffee.Order;

import static org.assertj.core.api.Assertions.assertThat;


public class DrinkMakerTest {
    DrinkMaker maker = new DrinkMaker();

    @Test
    public void can_print_how_many_drink_of_each_whas_sold() throws Exception {
        String printMsg = maker.printHowManyDrinks();
        assertThat(printMsg).isEqualTo("");

        buy(DrinkType.CHOCOLATE, 5.0);
        buy(DrinkType.CHOCOLATE, 5.0);
        buy(DrinkType.TEA, 5.0);

        printMsg = maker.printHowManyDrinks();
        assertThat(printMsg).contains("H:2;","T:1;").doesNotContain("C");

        buy(DrinkType.COFFEE, 5.0);
        buy(DrinkType.COFFEE, 5.0);
        buy(DrinkType.COFFEE, 5.0);

        printMsg = maker.printHowManyDrinks();
        assertThat(printMsg).contains("C:3;","T:1;","H:2;");
    }

    private void buy(DrinkType drinkType, double money) {
        Logic logic = new Logic(new Order(drinkType, 0, String.valueOf(money), "hot"));
        maker.make(logic.push());
    }

}
