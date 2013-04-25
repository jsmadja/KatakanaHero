package com.jsmadja.katakanahero.client;

import com.jsmadja.katakanahero.EmbeddedServer;
import com.jsmadja.katakanahero.domain.Katakana;
import com.jsmadja.katakanahero.domain.Player;
import com.jsmadja.katakanahero.domain.Progression;
import com.jsmadja.katakanahero.domain.Result;
import com.jsmadja.katakanahero.provider.JacksonMapperProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.fest.util.Files;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class KatakanaHeroClient {

    private final EmbeddedServer server;

    private Client client;

    private Collection<Player> players = new HashSet<Player>();

    public KatakanaHeroClient(EmbeddedServer server) {
        this.server = server;
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonMapperProvider.class);
        client = Client.create(cc);
    }

    public Player createNewPlayer(String playerName) {
        Player player = new Player(playerName);
        preparePostRequest(player, "players").post(Katakana.class);
        addPlayersToDelete(player);
        return player;
    }

    public Result answer(Player player, String answer) {
        return preparePostRequest(answer, "players/" + player.getName() + "/session/answers").post(Result.class);
    }

    public Katakana getCurrentKatakana(Player player) {
        return prepareGetRequest("players/"+player.getName()+"/session").get(Katakana.class);
    }

    public Progression getProgressionOf(Player player) {
        return prepareGetRequest("players/" + player.getName() + "/progression").get(Progression.class);
    }

    private void addPlayersToDelete(Player player) {
        players.add(player);
    }

    private String uriOf(String resource) {
        return server.uri + resource;
    }

    private WebResource.Builder preparePostRequest(Object object, String resource) {
        return client.resource(uriOf(resource)).accept(APPLICATION_JSON).entity(object, APPLICATION_JSON);
    }

    private WebResource.Builder prepareGetRequest(String resource) {
        return client.resource(uriOf(resource)).accept(APPLICATION_JSON);
    }

    public void deleteDatabaseOf(Player player) {
        Files.delete(new File("/tmp/katakanahero-" + player.getName() + ".db"));
    }

    public Collection<Player> getPlayers() {
        return players;
    }

}
