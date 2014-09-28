package fr.xebia.photobooth.domain;

import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;

public class PictureBuilder {

    private Colorimetry colorimetry = COLOR;
    private Format format = PORTRAIT;

    public static Picture aPicture() {
        return new PictureBuilder().build();
    }

    public Picture build() {
        return new Picture(colorimetry, format);
    }

    public PictureBuilder withColorimetry(Colorimetry colorimetry) {
        this.colorimetry = colorimetry;
        return this;
    }

    public PictureBuilder withFormat(Format format) {
        this.format = format;
        return this;
    }
}
