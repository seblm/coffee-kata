package fr.xebia.photobooth.domain;

import org.junit.Test;

import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Colorimetry.VINTAGE;
import static fr.xebia.photobooth.domain.Format.IDENTITY;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;
import static fr.xebia.photobooth.domain.PictureBuilder.aPicture;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {


    @Test
    public void should_validate_an_order_if_the_exact_picture_price_is_provided() {
        //given
        Order order = new OrderBuilder()
                .withMoney(aPicture().price())
                .build();

        //when
        Boolean orderValidated = Validation.INSTANCE.isValid(order);

        //then
        assertThat(orderValidated).isTrue();
    }

    @Test
    public void should_validate_an_order_if_more_than_picture_price_is_provided() {
        //given
        Order order = new OrderBuilder()
                .withMoney(aPicture().price() + 0.5)
                .build();

        //when
        Boolean orderValidated = Validation.INSTANCE.isValid(order);

        //then
        assertThat(orderValidated).isTrue();
    }

    @Test
    public void should_not_validate_an_order_if_less_than_picture_price_is_provided() {
        //given
        Order order = new OrderBuilder()
                .withMoney(aPicture().price() - 0.5)
                .build();

        //when
        Boolean orderValidated = Validation.INSTANCE.isValid(order);

        //then
        assertThat(orderValidated).isFalse();
    }

    @Test
    public void should_not_validate_an_order_if_it_is_for_identity_vintage_picture() {
        //given
        Picture picture = new PictureBuilder()
                .withColorimetry(VINTAGE)
                .withFormat(IDENTITY)
                .build();
        Order order = new OrderBuilder()
                .withPicture(picture)
                .withMoney(picture.price())
                .build();

        //when
        Boolean orderValidated = Validation.INSTANCE.isValid(order);

        //then
        assertThat(orderValidated).isFalse();
    }

}
