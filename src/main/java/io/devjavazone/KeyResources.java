package io.devjavazone;

import io.devjavazone.model.Key;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Path("/key")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KeyResources {

    private static final Map<Integer, Key> KEYS = new HashMap<>();

    @POST
    public Response create(Key key) {
        int id = (int) (Math.random() * 1000);
        key.setId(id);
        KEYS.put(id, key);

        return Response.created(URI.create("/key/" + id)).entity(key).build();
    }

    @PUT
    @Path("/{id}")
    public Response put(@PathParam Integer id, Key key) {
        Key keyParaAtualizar = KEYS.get(id);
        keyParaAtualizar.setHash(key.getHash());

        return Response.ok(keyParaAtualizar).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam Integer id) {
        KEYS.remove(id);

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Key get(@PathParam Integer id) {
        return KEYS.get(id);
    }



}