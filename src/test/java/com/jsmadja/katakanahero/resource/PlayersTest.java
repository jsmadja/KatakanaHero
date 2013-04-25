package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.Katakana;
import com.jsmadja.katakanahero.domain.Player;
import org.junit.After;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PlayersTest extends KatakanaITest {

    public static final String PLAYER_NAME = "test";

    @Test
    public void doit_demarrer_une_nouvelle_partie() {
        Player player = new Player(PLAYER_NAME);
        Katakana katakana = preparePostRequest(player, uriOf("players")).post(Katakana.class);
        assertThat(katakana).isNotNull();
    }

    @After
    public void deleteAll() {
        deleteDatabaseOf(PLAYER_NAME);
    }

    /*

    @Test
    public void shouldFindByName() {
        post(new Game("dark souls", "ps3"));

        List<Game> games = find("dark souls");
        assertThat(games).onProperty("name").containsExactly("dark souls");
    }

    @Test
    public void shouldFindByPartialName() {
        post(new Game("dark souls", "ps3"));

        List<Game> games = find("rk");
        assertThat(games).onProperty("name").containsExactly("dark souls");
    }

    @Test
    public void shouldFindForSeveralConsole() {
        post(new Game("dark souls", "ps3"));
        post(new Game("dark souls", "xbox360"));
        post(new Game("darksiders", "ps3"));
        post(new Game("darksiders 2", "xbox360"));

        List<Game> games = find("rk");
        assertThat(games).onProperty("name").contains("dark souls", "dark souls", "darksiders", "darksiders 2");
        assertThat(games).onProperty("console").contains("ps3", "xbox360");
    }

    @Test
    @Ignore
    public void shouldFindWithConsole() {
        post(new Game("dark souls", "ps3"));
        post(new Game("dark souls", "xbox360"));
        post(new Game("darksiders", "ps3"));
        post(new Game("darksiders 2", "xbox360"));

        List<Game> games = find("dark ps3");
        assertThat(games).onProperty("name").contains("dark souls", "darksiders");
        assertThat(games).onProperty("console").contains("ps3");
    }

    private List<Game> find(String name) {
        return client.resource(uri).queryParam("name", name).accept(APPLICATION_JSON).get(new GenericType<List<Game>>() {
        });
    }

    public Game get(Game game) {
        String id = game.getId();
        return get(id).getEntity(Game.class);
    }

    public ClientResponse get(String id) {
        return client.resource(uri).path(id).accept(APPLICATION_JSON).get(ClientResponse.class);
    }

    public Game post(Game game) {
        return client.resource(uri).accept(APPLICATION_JSON).entity(game, APPLICATION_JSON).post(Game.class);
    }
*/
}
