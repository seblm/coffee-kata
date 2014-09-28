package fr.xebia.photobooth.domain;

public class PictureProcessorColorimetry implements Colorimetry.ColorimetryVisitor {
    @Override
    public String visitColor() {
        return "C";
    }
}
