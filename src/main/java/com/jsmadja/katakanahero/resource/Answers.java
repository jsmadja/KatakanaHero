package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.*;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("answers")
public class Answers {

    @HeaderParam("player")
    private Player player;

    private KatakanaHero katakanaHero = KatakanaHero.getInstance();

    @POST
    public Response post(String answer) {
        GameSession gameSession = katakanaHero.getSessionOf(player);
        Result result = gameSession.input(answer);
        return Response.ok(result).build();
    }

}
