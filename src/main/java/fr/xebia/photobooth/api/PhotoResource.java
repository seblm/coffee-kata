package fr.xebia.photobooth.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.xebia.photobooth.domain.Logic;
import fr.xebia.photobooth.domain.Order;


@Path("/photos")
@Produces(MediaType.APPLICATION_JSON)
public class PhotoResource {
	@POST
    @Path("/create")
	public void newPhoto(Order order) {
		Logic.INSTANCE.run(order);
	}	
}
