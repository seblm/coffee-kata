package fr.xebia.photobooth.api;

import fr.xebia.photobooth.domain.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


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

    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveToFile(String base64Picture) throws IOException {
        byte[] data = Base64.getDecoder().decode(base64Picture);
        File urlFile = File.createTempFile("image", ".png");

        try (OutputStream stream = new FileOutputStream(urlFile);) {
            stream.write(data);
        }

        return urlFile.getName();
    }

    @POST
    @Path("/saveWithURL")
    @Consumes(MediaType.APPLICATION_JSON)
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
