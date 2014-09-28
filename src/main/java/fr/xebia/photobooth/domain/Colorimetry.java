package fr.xebia.photobooth.domain;

public enum Colorimetry {
    COLOR() {
        public String accept(ColorimetryVisitor visitor) {
            return visitor.visitColor();
        }
    };

    public abstract String accept(ColorimetryVisitor visitor);

    public interface ColorimetryVisitor {

        String visitColor();
    }
}

