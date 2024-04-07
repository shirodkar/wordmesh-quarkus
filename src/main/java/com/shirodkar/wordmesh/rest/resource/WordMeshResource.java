package com.shirodkar.wordmesh.rest.resource;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
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

    @ConfigProperty(name = "letterCase", defaultValue = "upper")
    String letterCase;

    @GET
    @Path("start/word/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String start(@PathParam("word") String word) throws URISyntaxException {
        word = setCase(word);
        String firstLetter = word.substring(0, 1);
        return "The word -" + word + "- is traversing the mesh...\nGive me " + firstLetter + "..." + getLetterClient(firstLetter).bounce(word, firstLetter, 1);
    }

    @GET
    @Path("/bounce/word/{word}/letter/{letter}/index/{index}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String bounce(@PathParam("word") String word, @PathParam("letter") String letter, @PathParam("index") int index) throws URISyntaxException {
        if(word.length() == index) {
            return letter + "!!!" + endClient.end(word);
        }
        String nextLetter = word.substring(index, index+1);
        
        return letter + "!!!\nGive me " + nextLetter + "..." + getLetterClient(nextLetter).bounce(word, nextLetter, index+1);
    }

    @GET
    @Path("end/word/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String end(@PathParam("word") String word) {
        return "\n" + word + " " + word + " " + word + " " + word + "!!!";
    }

    private LetterClient getLetterClient(String letter) throws URISyntaxException {
        URI apiUri = new URI("http://letter-" + letter + ":8080");
        return RestClientBuilder.newBuilder().baseUri(apiUri).build(LetterClient.class);
    }

    private String setCase(String word) {
        return ("upper".equals(letterCase)) ?  word.toUpperCase() : word.toLowerCase();
    }
}
