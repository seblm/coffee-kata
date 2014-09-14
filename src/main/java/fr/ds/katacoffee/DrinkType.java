package fr.ds.katacoffee;

import java.math.BigDecimal;

public enum DrinkType {
    COFFEE("0.6"), TEA("0.4"), CHOCOLATE("0.5"), ORANGE("0.6");

    final BigDecimal cost;

    DrinkType(String cost) {
        this.cost = new BigDecimal(cost);
    }
}
