package fr.ds.katacoffee;

public class Order {
    DrinkType drinkType;
    int numOfSugar;

    public Order(DrinkType drinkType, int numOfSugar) {
        this.drinkType = drinkType;
        this.numOfSugar = numOfSugar;
    }
}
