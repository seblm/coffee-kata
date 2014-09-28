package fr.xebia.photobooth.domain;

import org.junit.Test;

import static fr.xebia.photobooth.domain.Colorimetry.BLACK_AND_WHITE;
import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Colorimetry.VINTAGE;
import static fr.xebia.photobooth.domain.Format.IDENTITY;
import static fr.xebia.photobooth.domain.Format.MINI;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderToPictureProcessorProtocolTest {

    OrderToPictureProcessorProtocol orderToPictureProcessorProtocol = new OrderToPictureProcessorProtocol();

    @Test
    public void should_convert_full_color_portrait_order(){
        //given
        Order order = new OrderBuilder()
                .withPicture(new PictureBuilder()
                        .withColorimetry(COLOR)
                        .withFormat(PORTRAIT)
                        .build())
                .build();

        //when
        String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(order);

        //then
        assertThat(pictureProcessorOrder).isEqualTo("C;P");
    }

    @Test
    public void should_convert_full_color_identity_order(){
        //given
        Order order = new OrderBuilder()
                .withPicture(new PictureBuilder()
                        .withColorimetry(COLOR)
                        .withFormat(IDENTITY)
                        .build())
                .build();

        //when
        String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(order);

        //then
        assertThat(pictureProcessorOrder).isEqualTo("C;I");
    }

    @Test
    public void should_convert_black_and_white_identity_order(){
        //given
        Order order = new OrderBuilder()
                .withPicture(new PictureBuilder()
                        .withColorimetry(BLACK_AND_WHITE)
                        .withFormat(IDENTITY)
                        .build())
                .build();

        //when
        String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(order);

        //then
        assertThat(pictureProcessorOrder).isEqualTo("BW;I");
    }

    @Test
    public void should_convert_vintage_mini_order(){
        //given
        Order order = new OrderBuilder()
                .withPicture(new PictureBuilder()
                        .withColorimetry(VINTAGE)
                        .withFormat(MINI)
                        .build())
                .build();

        //when
        String pictureProcessorOrder = orderToPictureProcessorProtocol.convert(order);

        //then
        assertThat(pictureProcessorOrder).isEqualTo("V;M");
    }
}
