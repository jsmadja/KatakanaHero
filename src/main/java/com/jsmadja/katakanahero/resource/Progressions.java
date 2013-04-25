package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.*;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("progressions")
public class Progressions {

    @HeaderParam("player")
    private Player player;

    private KatakanaHero katakanaHero = KatakanaHero.getInstance();

    @GET
    public Response get() {
        GameSession gameSession = katakanaHero.getSessionOf(player);
        Progression progression = gameSession.getProgression();
        return Response.ok(progression).build();
    }

}
