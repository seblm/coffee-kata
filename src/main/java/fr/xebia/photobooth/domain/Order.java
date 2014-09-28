package fr.xebia.photobooth.domain;

import java.math.BigDecimal;


public class Order {
    public final Format format;
    public final Colorimetry colorimetry;
    public final BigDecimal money;

    public Order(Colorimetry colorimetry, Format portrait, BigDecimal money) {
        this.colorimetry = colorimetry;
        this.format = portrait;
        this.money = money;
    }

}
