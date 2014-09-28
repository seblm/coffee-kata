package fr.xebia.photobooth.external.service;

import java.io.File;
import java.util.Random;

public class IdentityValidator implements Validator {

    private Random random;

    public IdentityValidator() {
        this.random = new Random();
    }

    @Override
    public Boolean validate(File picture) {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextBoolean();
    }
}
