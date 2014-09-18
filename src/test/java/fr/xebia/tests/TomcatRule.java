package fr.xebia.tests;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

import static org.apache.catalina.WebResourceRoot.ResourceSetType.POST;

public class TomcatRule {
    private Tomcat tomcat;

    public void before() throws Throwable {
        tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context ctx = tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());
        StandardRoot resources = new StandardRoot(ctx);
        resources.createWebResourceSet(POST, "/WEB-INF/classes", new File("target/classes").toURI().toURL(), "/");
        ctx.setResources(resources);

        tomcat.start();
    }

    public void after() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
