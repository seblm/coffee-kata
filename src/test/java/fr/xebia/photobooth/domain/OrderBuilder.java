package fr.xebia.photobooth.domain;

import java.math.BigDecimal;

import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;

public class OrderBuilder {

    private Colorimetry colorimetry = COLOR;
    private Format format = PORTRAIT;
    private BigDecimal money = new BigDecimal(2.5);

    public Order build(){
        return new Order(colorimetry, format, money);
    }

    public static Order anOrder(){
        return new OrderBuilder().build();
    }

    public OrderBuilder withColorimetry(Colorimetry colorimetry){
        this.colorimetry = colorimetry;
        return this;
    }

    public OrderBuilder withFormat(Format format){
        this.format = format;
        return this;
    }

    public OrderBuilder withMoney(BigDecimal money){
        this.money = money;
        return this;
    }
}
