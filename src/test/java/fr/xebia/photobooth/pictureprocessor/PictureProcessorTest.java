package fr.xebia.photobooth.pictureprocessor;

import com.google.common.io.Resources;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class PictureProcessorTest {

    PictureProcessor pictureProcessor = new PictureProcessor();

    @Test
    public void should_apply_classic_potrait_sepia() throws Exception {
        writeModifiedSamplePicture(pictureProcessor.process("V:P", getSamplePicture()));
    }

    @Test
    public void should_apply_official_identity() throws Exception {
        writeModifiedSamplePicture(pictureProcessor.process("C:I", getSamplePicture()));
    }

    @Test
    public void should_apply_classic_mini_nb() throws Exception {
        writeModifiedSamplePicture(pictureProcessor.process("BW:M", getSamplePicture()));
    }

    private File getSamplePicture() throws URISyntaxException {
        return new File(Resources.getResource("dog.jpeg").toURI());
    }

    private void writeModifiedSamplePicture(Image processedPicture) throws IOException {
        File outputfile = new File("modifiedDogs.jpeg");
        ImageIO.write((java.awt.image.RenderedImage) processedPicture, "jpeg", outputfile);
    }
}
