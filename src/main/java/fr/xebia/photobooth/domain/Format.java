package fr.xebia.photobooth.domain;

public enum Format {
    PORTRAIT() {
        public String accept(FormatVisitor visitor) {
            return visitor.visitPortrait();
        }
    };

    public abstract String accept(FormatVisitor visitor);

    public interface FormatVisitor {

        String visitPortrait();
    }
}
