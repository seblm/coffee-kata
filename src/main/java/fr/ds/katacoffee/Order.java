package fr.ds.katacoffee;

import java.math.BigDecimal;

public class Order {
    final String extraHot;
    DrinkType drinkType;
    int numOfSugar;
    public BigDecimal money;

    public Order(DrinkType drinkType, int numOfSugar, String money, String hot) {
        this.drinkType = drinkType;
        this.numOfSugar = numOfSugar;
        this.money = new BigDecimal(money);
        this.extraHot = hot;
    }
}
