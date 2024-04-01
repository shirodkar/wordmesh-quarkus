package com.shirodkar.wordmesh.rest.resource;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.shirodkar.wordmesh.rest.client.EndClient;
import com.shirodkar.wordmesh.rest.client.LetterClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class WordMeshResource {

    @RestClient
    EndClient endClient;

    @GET
    @Path("start/word/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String start(@PathParam("word") String word) throws URISyntaxException {
        String firstLetter = word.substring(0, 1);
        return "The word -" + word + "- is traversing the mesh...\nGive me " + firstLetter.toUpperCase() + "..." + getLetterClient(firstLetter).bounce(word, firstLetter, 1);
    }

    @GET
    @Path("/bounce/word/{word}/letter/{letter}/index/{index}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String bounce(@PathParam("word") String word, @PathParam("letter") String letter, @PathParam("index") int index) throws URISyntaxException {
        if(word.length() == index) {
            return letter + endClient.end(word);
        }
        String nextLetter = word.substring(index, index+1);
        
        return letter.toUpperCase() + "!!!\nGive me " + nextLetter.toUpperCase() + "..." + getLetterClient(nextLetter).bounce(word, nextLetter, index+1);
    }

    @GET
    @Path("end/word/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String end(@PathParam("word") String word) {
        return "\n" + word.toUpperCase() + " " + word.toUpperCase() + " " + word.toUpperCase() + " " + word.toUpperCase() + "!!!";
    }

    private LetterClient getLetterClient(String letter) throws URISyntaxException {
        URI apiUri = new URI("http://letter-" + letter + ":8080");
        return RestClientBuilder.newBuilder().baseUri(apiUri).build(LetterClient.class);
    }

}
