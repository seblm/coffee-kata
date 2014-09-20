package fr.xebia.katacoffee.bdd;

import fr.xebia.katacoffee.DrinkType;
import fr.xebia.katacoffee.Order;

public class OrderBuilder {

    private DrinkType drinkType = DrinkType.TEA;
    private int numberOfSugar = 0;
    private String money = "2";
    private String hot = "";

    public Order build(){
        return new Order(drinkType, numberOfSugar, money, hot);
    }

    public static Order anOrder(){
        return new OrderBuilder().build();
    }

    public OrderBuilder withDrinkType(DrinkType drinkType){
        this.drinkType = drinkType;
        return this;
    }

    public OrderBuilder withNumberOfSugar(int numberOfSugar){
        this.numberOfSugar = numberOfSugar;
        return this;
    }

    public OrderBuilder withMoney(String money){
        this.money = money;
        return this;
    }

    public OrderBuilder withHot(String hot){
        this.hot = hot;
        return this;
    }

}
