package com.shirodkar.wordmesh.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/wordmesh/end/word/{word}")
public interface EndClient {

    @GET
    String end(String word);

}
