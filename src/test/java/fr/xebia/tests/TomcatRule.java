package fr.xebia.tests;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.rules.ExternalResource;

import javax.servlet.ServletException;
import java.io.File;
import java.net.MalformedURLException;

import static org.apache.catalina.WebResourceRoot.ResourceSetType.POST;

public class TomcatRule  extends ExternalResource {
    private Tomcat tomcat;

    public void before() {
        tomcat = new Tomcat();
        tomcat.setPort(8080);

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

    public void after() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
