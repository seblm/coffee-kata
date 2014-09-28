package fr.xebia.photobooth.domain;

import java.math.BigDecimal;


public class Order {
    public final Format format;
    public final Colorimetry colorimetry;
    public final BigDecimal money;

    public Order(Colorimetry colorimetry, Format format, BigDecimal money) {
        this.colorimetry = colorimetry;
        this.format = format;
        this.money = money;
    }

    public Order(String colorimetry, String format, String money) {
        this.colorimetry = Colorimetry.valueOf(colorimetry);
        this.format = Format.valueOf(format);
        this.money = new BigDecimal(money);
    }

}
