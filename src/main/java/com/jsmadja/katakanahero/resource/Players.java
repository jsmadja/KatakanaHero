package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.GameSession;
import com.jsmadja.katakanahero.domain.Katakana;
import com.jsmadja.katakanahero.domain.KatakanaHero;
import com.jsmadja.katakanahero.domain.Player;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("players")
public class Players {

    @POST
    public Response post(Player player) {
        GameSession gameSession = KatakanaHero.getInstance().getSessionOf(player);
        Katakana katakana = gameSession.next();
        return Response.ok(katakana).build();
    }

}
