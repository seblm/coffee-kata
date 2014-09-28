package fr.xebia.photobooth.domain;

public enum Validation {
    INSTANCE;

    public Boolean isValid(Order order){
        return true;
    }
}
