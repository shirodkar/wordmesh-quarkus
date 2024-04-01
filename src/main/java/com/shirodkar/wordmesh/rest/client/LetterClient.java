package com.shirodkar.wordmesh.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/wordmesh/bounce/word/{word}/letter/{letter}/index/{index}")
public interface LetterClient {

    @GET
    String bounce(String word, String letter, int index);

}