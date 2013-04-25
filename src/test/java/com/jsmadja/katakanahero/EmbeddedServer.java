package com.jsmadja.katakanahero;

import com.jsmadja.katakanahero.Server;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.net.URI;

public class EmbeddedServer extends ExternalResource {

    public URI uri;

    private EmbeddedServer(URI uri) {
        this.uri = uri;
    }

    public static EmbeddedServer create() {
        try {
            return new EmbeddedServer(new Server().uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
