package fr.xebia.photobooth.domain;

import fr.xebia.photobooth.domain.Photo.PhotoType;

public class Command {
    PhotoType photoType;
    boolean color;

    public Command(PhotoType photoType, boolean color) {
        this.photoType = photoType;
        this.color = color;
    }
}
