package fr.xebia.photobooth.domain;


import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.xebia.photobooth.domain.Photo.PhotoType;


import static org.assertj.core.api.Assertions.assertThat;


public class LogicTest {
	 @Rule
	 public ExpectedException thrown = ExpectedException.none();
	 
	@Test
	@Ignore
    public void should_not_run_command_if_not_enough_money() {
        thrown.expect(MachineException.class);
        thrown.expectMessage("not enougn money");

        Order order = new Order(PhotoType.IDENTITY_COLOR, true, 0.0);
		Logic.INSTANCE.run(order);
    }
	
    @Test
    public void should_run_command_for_photo() throws Exception {
        Order order = new Order(PhotoType.IDENTITY_COLOR, true, 2.0);
		String output = Logic.INSTANCE.run(order);
		
        assertThat(output).isEqualTo("YOUR PHOTO IS READY");
    }

}
