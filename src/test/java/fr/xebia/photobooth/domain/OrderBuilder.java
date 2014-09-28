package fr.xebia.photobooth.domain;

import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;

public class OrderBuilder {

    private Double money = 2.0;
    private Picture picture = new Picture(COLOR, PORTRAIT);

    public static Order anOrder() {
        return new OrderBuilder().build();
    }

    public Order build() {
        return new Order(picture, money);
    }

    public OrderBuilder withMoney(Double money) {
        this.money = money;
        return this;
    }

    public OrderBuilder withPicture(Picture picture) {
        this.picture = picture;
        return this;
    }
}
