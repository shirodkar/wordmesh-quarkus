package com.shirodkar.wordmesh.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/wordmesh/end/word/{word}")
@RegisterRestClient(baseUri="http://end:8080")
public interface EndClient {

    @GET
    String end(String word);

}
