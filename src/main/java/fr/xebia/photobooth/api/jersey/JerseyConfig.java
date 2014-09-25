package fr.xebia.photobooth.api.jersey;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {	
    public JerseyConfig() {
        register(JacksonFeature.class);
        packages("fr.xebia.photobooth.api");
    }
}
