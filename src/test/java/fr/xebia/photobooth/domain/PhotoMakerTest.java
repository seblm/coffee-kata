package fr.xebia.photobooth.domain;

import fr.xebia.photobooth.external.pictureprocessor.PictureProcessor;
import fr.xebia.photobooth.external.pictureprocessor.PictureProcessorException;
import fr.xebia.photobooth.external.service.Validator;
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
import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Format.IDENTITY;
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
    @Mock
    private Validator identityValidator;

    public PhotoMaker photoMaker;

    @Before
    public void setup() {
        this.photoMaker = new PhotoMaker(pictureProcessor, identityValidator);
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
        when(pictureProcessor.process(anyString(), eq(pictureToProcess))).thenThrow(new PictureProcessorException(null));

        //when
        catchException(photoMaker).make(command);

        //then
        then(caughtException())
                .isInstanceOf(MachineException.class)
                .hasMessage("Unexpected error when processing picture");
    }

    @Test
    public void should_validate_identity_picture_before_sending_it_to_picture_processor() throws Exception {
        //given
        File pictureToProcess = folder.newFile();
        Order order = new OrderBuilder()
                .withPicture(new PictureBuilder()
                        .withFormat(IDENTITY)
                        .build())
                .build();
        Command command = new Command(order, pictureToProcess);
        when(identityValidator.validate(eq(pictureToProcess))).thenReturn(false);

        //when
        catchException(photoMaker).make(command);

        //then
        then(caughtException())
                .isInstanceOf(NotAValidIdentityPicture.class)
                .hasMessage("This picture does not respect identity picture standard");
    }

}
