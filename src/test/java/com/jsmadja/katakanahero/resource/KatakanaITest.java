package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.EmbeddedServer;
import com.jsmadja.katakanahero.provider.JacksonMapperProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.fest.util.Files;
import org.junit.Before;
import org.junit.ClassRule;

import java.io.File;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public abstract class KatakanaITest {

    @ClassRule
    public static EmbeddedServer server = EmbeddedServer.create();

    private Client client;

    @Before
    public void createJacksonCustomClient() {
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonMapperProvider.class);
        client = Client.create(cc);
    }

    protected void deleteDatabaseOf(String playerName) {
        Files.delete(new File("/tmp/katakanahero-" + playerName + ".db"));
    }

    protected String uriOf(String resource) {
        return server.uri + resource;
    }

    protected WebResource.Builder preparePostRequest(Object object, String resource) {
        return client.resource(uriOf(resource)).accept(APPLICATION_JSON).entity(object, APPLICATION_JSON);
    }

    protected WebResource.Builder prepareGetRequest(String resource) {
        return client.resource(uriOf(resource)).accept(APPLICATION_JSON);
    }

}
