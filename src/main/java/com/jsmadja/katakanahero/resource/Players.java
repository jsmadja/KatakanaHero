package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("players")
public class Players {

    private KatakanaHero katakanaHero = KatakanaHero.getInstance();

    @POST
    public Response post(Player player) {
        GameSession gameSession = sessionOf(player);
        Katakana katakana = gameSession.next();
        return Response.ok(katakana).build();
    }

    @GET
    @Path("{name}/session")
    public Response getSession(@PathParam("name") Player player) {
        GameSession gameSession = sessionOf(player);
        Katakana katakana = gameSession.getCurrentKatakana();
        return Response.ok(katakana).build();
    }

    @POST
    @Path("{name}/session/answers")
    public Response post(@PathParam("name") Player player, String answer) {
        GameSession gameSession = sessionOf(player);
        Result result = gameSession.input(answer);
        return Response.ok(result).build();
    }

    @GET
    @Path("{name}/progression")
    public Response get(@PathParam("name") Player player) {
        GameSession gameSession = sessionOf(player);
        Progression progression = gameSession.getProgression();
        return Response.ok(progression).build();
    }

    private GameSession sessionOf(Player player) {
        return katakanaHero.getSessionOf(player);
    }

}
