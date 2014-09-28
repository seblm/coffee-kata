package fr.xebia.photobooth.domain;

import fr.xebia.photobooth.external.pictureprocessor.PictureProcessor;
import fr.xebia.photobooth.external.pictureprocessor.PictureProcessorException;
import fr.xebia.photobooth.external.service.Validator;

import java.io.File;

import static fr.xebia.photobooth.domain.Format.IDENTITY;

public class PhotoMaker {

    private final PictureProcessor pictureProcessor;
    private final OrderToPictureProcessorProtocol orderToPictureProcessorProtocol;
    private final Validator identityValidator;

    public PhotoMaker(PictureProcessor pictureProcessor, Validator identityValidator) {
        this.pictureProcessor = pictureProcessor;
        this.identityValidator = identityValidator;
        this.orderToPictureProcessorProtocol = new OrderToPictureProcessorProtocol();
    }

    public File make(Command command) throws MachineException, NotAValidIdentityPicture {
        try {
            validateIdentityPicture(command);
            String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(command.order);
            return pictureProcessor.process(pictureProcessorOrder, command.picture);
        } catch (PictureProcessorException e) {
            throw new MachineException("Unexpected error when processing picture", e);
        }
    }

    private void validateIdentityPicture(Command command) throws NotAValidIdentityPicture {
        if (command.order.picture.format == IDENTITY) {
            if (!identityValidator.validate(command.picture)) {
                throw new NotAValidIdentityPicture("This picture does not respect identity picture standard");
            }
        }
    }
}
