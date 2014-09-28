package fr.xebia.photobooth.domain;

import fr.xebia.photobooth.external.pictureprocessor.PictureProcessor;

import java.io.File;

public class PhotoMaker {

    private final PictureProcessor pictureProcessor;
    private final OrderToPictureProcessorProtocol orderToPictureProcessorProtocol;

    public PhotoMaker(PictureProcessor pictureProcessor) {
        this.pictureProcessor = pictureProcessor;
        this.orderToPictureProcessorProtocol = new OrderToPictureProcessorProtocol();
    }

    public File make(Command command) throws MachineException {
        try {
            String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(command.order);
            return pictureProcessor.process(pictureProcessorOrder, command.picture);
        } catch (Exception e) {
            throw new MachineException("Unexpected error when processing picture", e);
        }
    }
}
