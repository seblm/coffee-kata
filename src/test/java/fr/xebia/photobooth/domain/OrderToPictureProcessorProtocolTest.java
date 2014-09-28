package fr.xebia.photobooth.domain;

import org.junit.Test;

import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderToPictureProcessorProtocolTest {

    OrderToPictureProcessorProtocol orderToPictureProcessorProtocol = new OrderToPictureProcessorProtocol();

    @Test
    public void should_convert_full_color_portrait_order(){
        //given
        Order order = new OrderBuilder()
                .withColorimetry(COLOR)
                .withFormat(PORTRAIT)
                .build();

        //when
        String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(order);

        //then
        assertThat(pictureProcessorOrder).isEqualTo("C;P");
    }
}
