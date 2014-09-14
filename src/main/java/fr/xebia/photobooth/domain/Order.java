package fr.xebia.photobooth.domain;

import java.math.BigDecimal;

import fr.xebia.photobooth.domain.Photo.PhotoType;


public class Order {
    PhotoType photoType;
    boolean color;
    public BigDecimal money;

    public Order(PhotoType photoType, boolean color, double money) {
        this.photoType = photoType;
        this.color = color;
        this.money = new BigDecimal(money);
    }
}
