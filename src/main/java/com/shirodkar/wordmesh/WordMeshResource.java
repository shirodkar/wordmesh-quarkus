package com.shirodkar.wordmesh;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("bounce")
public class WordMeshResource {

    @GET
    @Path("/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String bounce(@PathParam("word") String word) {
        return "The word -" + word + "- was bounced off the mesh!";
    }
}
