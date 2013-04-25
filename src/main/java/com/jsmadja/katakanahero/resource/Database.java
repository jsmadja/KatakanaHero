package com.jsmadja.katakanahero.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("database")
public class Database {

    @GET
    @Path("/")
    public Response get() {
        String content = "OK";
        return Response.ok(content).build();
    }

    /*
    private Shop shop = new Shop();

    private MongoCollection games;

    public Games() throws UnknownHostException {
        games = Server.getCollection("games");
    }

    @GET
    public Response find(@QueryParam("name") String name) {
        if(name == null)
            return Response.status(BAD_REQUEST).build();
        if(name.length() < 3) {
            return Response.ok(new GenericEntity<List<Game>>(new ArrayList<Game>()) {}).build();
        }
        Iterable<Game> all = games.find("{name: {$regex: #}}", name + ".*").as(Game.class);
        List<Game> allGames = Lists.newArrayList(all);
        Collections.sort(allGames);
        return Response.ok(new GenericEntity<List<Game>>(allGames) {}).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        if (!isValid(id))
            return Response.status(NOT_FOUND).build();

        Game one = games.findOne(withOid(id)).as(Game.class);
        return Response.ok(one).build();
    }

    @POST
    public Response post(Game game) {
        games.save(game);
        return Response.ok(game).build();
    }

    @DELETE
    public Response delete() {
        games.remove();
        return Response.ok().build();
    }

    @GET
    @Path("/import")
    public Response importGamesInDb() {
        Collection<Game> games = shop.all();
        for (Game game : games) {
            post(game);
        }
        return Response.ok(games.size()).build();
    }
    */
}
