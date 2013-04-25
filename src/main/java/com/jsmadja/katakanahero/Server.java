package com.jsmadja.katakanahero;

import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

import static com.sun.jersey.api.core.ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS;
import static com.sun.jersey.api.core.ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS;

public class Server {

    public URI uri;

    Server() throws IOException {
        String port = System.getenv("PORT");

        if (isHeroku())
            uri = UriBuilder.fromUri("http://0.0.0.0/").port(Integer.valueOf(port)).path("/resource/").build();
        else
            uri = UriBuilder.fromUri("http://localhost/").port(3000).path("/resource/").build();

        ResourceConfig rc = newConfig();
        GrizzlyServerFactory.createHttpServer(uri, rc).getServerConfiguration().addHttpHandler(new StaticHttpHandler("src/main/webapp"), "/");
    }

    private static ResourceConfig newConfig() {
        ResourceConfig rc = new PackagesResourceConfig("com.jsmadja.katakanahero.resource", "com.jsmadja.katakanahero.provider");
        rc.getProperties().put(PROPERTY_CONTAINER_REQUEST_FILTERS, new LoggingFilter());
        rc.getProperties().put(PROPERTY_CONTAINER_RESPONSE_FILTERS, new LoggingFilter());
        return rc;
    }

    public static boolean isHeroku() {
        return System.getenv("PORT") != null;
    }

    // Heroku startup
    public static void main(String[] args) throws IllegalArgumentException, IOException {
        new Server();
        while (true) System.in.read();
    }
}
