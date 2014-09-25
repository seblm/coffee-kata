package fr.xebia.photobooth.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public String saveToFile(@PathParam("picture") String base64Picture) throws IOException {
        byte[] data = Base64.getDecoder().decode(base64Picture);
        File urlFile = File.createTempFile("image", ".png");

        try (OutputStream stream = new FileOutputStream(urlFile);) {
            stream.write(data);
        }

        return urlFile.getName();
    }

    @POST
    @Path("/saveWithURL")
    public String saveToFileWithURL(String pictureUrl) throws IOException {
        File targetFile = File.createTempFile("image", ".png");
        targetFile.delete();

        try (InputStream in = new URL(pictureUrl).openStream()) {
            Files.copy(in, targetFile.toPath());
        }

        return targetFile.getName();
    }

	
	/*
     *
	 * // Note preferred way of declaring an array variable
	 */
}
