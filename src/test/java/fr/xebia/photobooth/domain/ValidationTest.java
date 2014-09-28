package fr.xebia.photobooth.domain;

import org.junit.Test;

import static fr.xebia.photobooth.domain.Colorimetry.COLOR;
import static fr.xebia.photobooth.domain.Format.PORTRAIT;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    @Test
    public void should_always_validate_a_full_color_portrait_order() {
        //given
        Order order = new OrderBuilder()
                .withColorimetry(COLOR)
                .withFormat(PORTRAIT)
                .build();

        //when
        Boolean orderValidated = Validation.INSTANCE.isValid(order);

        //then
        assertThat(orderValidated).isTrue();
    }

}
