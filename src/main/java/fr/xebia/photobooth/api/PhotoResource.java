package fr.xebia.photobooth.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.xebia.photobooth.domain.Logic;
import fr.xebia.photobooth.domain.Order;


@Path("/photos")
@Produces(MediaType.APPLICATION_JSON)
public class PhotoResource {

	@GET
	public String getHelloWorld() {
		return "Hello world";
	}	
	
	
	@POST
    @Path("/create")
	public void newPhoto(Order order) {
		Logic.INSTANCE.run(order);
	}	

	@POST
    @Path("/save")
	public String saveToFile(String base64Picture) {
		File urlFile = null;
		byte[] data = Base64.getDecoder().decode(base64Picture);
		
		try {
			urlFile = File.createTempFile("image", ".png");
			OutputStream stream = new FileOutputStream(urlFile);
			stream.write(data);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return urlFile.getAbsolutePath();
	}	
	
	
	/*
	 * 
	 * // Note preferred way of declaring an array variable
	 */
}
