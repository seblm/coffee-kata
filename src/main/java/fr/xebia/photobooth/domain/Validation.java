package fr.xebia.photobooth.domain;

import static fr.xebia.photobooth.domain.Colorimetry.VINTAGE;
import static fr.xebia.photobooth.domain.Format.IDENTITY;

public enum Validation {
    INSTANCE;

    public Boolean isValid(Order order){
        return validateEnoughAmountOfMoney(order) &&
                validatePictureCombinaison(order.picture);
    }

    private boolean validatePictureCombinaison(Picture picture) {
        return !(picture.colorimetry == VINTAGE &&
                picture.format == IDENTITY);
    }

    private boolean validateEnoughAmountOfMoney(Order order) {
        return order.money >= order.picture.price();
    }
}
