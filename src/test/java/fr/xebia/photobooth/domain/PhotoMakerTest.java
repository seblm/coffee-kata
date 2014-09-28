package fr.xebia.photobooth.domain;

import fr.xebia.photobooth.external.pictureprocessor.PictureProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.then;
import static fr.xebia.photobooth.domain.OrderBuilder.anOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhotoMakerTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Mock
    public PictureProcessor pictureProcessor;
    public PhotoMaker photoMaker;

    @Before
    public void setup() {
        this.photoMaker = new PhotoMaker(pictureProcessor);
    }

    @Test
    public void should_process_picture() throws Exception {
        //given
        File pictureToProcess = folder.newFile();
        File expectedPictureProcessed = folder.newFile();
        Command command = new Command(anOrder(), pictureToProcess);
        when(pictureProcessor.process(anyString(), eq(pictureToProcess))).thenReturn(expectedPictureProcessed);

        //when
        File pictureProcessed = photoMaker.make(command);

        //then
        assertThat(pictureProcessed).isEqualTo(expectedPictureProcessed);
    }

    @Test
    public void should_throw_machine_exception_when_picture_processor_fail() throws Exception {
        //given
        File pictureToProcess = folder.newFile();
        Command command = new Command(anOrder(), pictureToProcess);
        when(pictureProcessor.process(anyString(), eq(pictureToProcess))).thenThrow(new IllegalArgumentException());

        //when
        catchException(photoMaker).make(command);

        //then
        then(caughtException())
                .isInstanceOf(MachineException.class)
                .hasMessage("Unexpected error when processing picture");
    }

}
