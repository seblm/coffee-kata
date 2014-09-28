package fr.xebia.photobooth.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PhotoResourceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PhotoResource photoResource;

    @Before
    public void init() {
    	photoResource = new PhotoResource();
    }
    
    @Test
    public void should_return_hello_world_string() {
    	String output = photoResource.getHelloWorld();
    	assertThat(output).isEqualTo("Hello world");
    }    
    @Test
    public void should_save_to_file_base_64_string() throws IOException {
    	String base64String = "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";
    	String fileName = photoResource.saveToFile(base64String);
    	String absFilePath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
    	File file = new File(absFilePath);
    	assertThat(file.exists()).isTrue();
        BufferedImage image = ImageIO.read(file);
        assertThat(image.getHeight()).isEqualTo(48);
        assertThat(image.getWidth()).isEqualTo(48);
    }

    @Test
    public void should_save_to_file_url_string() throws IOException {
        String localFileTouDownload = this.getClass().getResource("/image.png").toString();
		String fileName = photoResource.saveToFileWithURL(localFileTouDownload);

    	String absFilePath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
    	File file = new File(absFilePath);
    	assertThat(file.exists()).isTrue();
        BufferedImage image = ImageIO.read(file);
        assertThat(image.getHeight()).isEqualTo(48);
        assertThat(image.getWidth()).isEqualTo(48);
    }

}
