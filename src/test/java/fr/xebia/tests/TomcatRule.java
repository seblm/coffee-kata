package fr.xebia.tests;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.rules.ExternalResource;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;

import static org.apache.catalina.WebResourceRoot.ResourceSetType.POST;

public class TomcatRule  extends ExternalResource {
    private Tomcat tomcat;

    public void before() {
        tomcat = new Tomcat();
        tomcat.setPort(randomPort());

        try {
            Context ctx = tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());
            StandardRoot resources = new StandardRoot(ctx);
            resources.createWebResourceSet(POST, "/WEB-INF/classes", new File("target/classes").toURI().toURL(), "/");
            ctx.setResources(resources);

            tomcat.start();
        } catch (ServletException | MalformedURLException | LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer randomPort() {
        try (ServerSocket server = new ServerSocket(0)) {
            return server.getLocalPort();
        } catch (IOException e) {
            return randomPort();
        }
    }

    public void after() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    public Integer port() {
        return tomcat.getConnector().getLocalPort();
    }
}
