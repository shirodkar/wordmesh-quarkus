package com.shirodkar.wordmesh.rest.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.shirodkar.wordmesh.rest.client.LetterAClient;
import com.shirodkar.wordmesh.rest.client.LetterBClient;
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
    LetterAClient letterAClient;

    @RestClient
    LetterBClient letterBClient;

    @GET
    @Path("/word/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String start(@PathParam("word") String word) {
        String firstLetter = word.substring(0, 1);
        return "The word -" + word + "- is traversing the mesh...\nGive me " + firstLetter + "..." + getLetterClient(firstLetter).bounce(word, firstLetter, 1);
    }

    @GET
    @Path("/bounce/word/{word}/letter/{letter}/index/{index}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String bounce(@PathParam("word") String word, @PathParam("letter") String letter, @PathParam("index") int index) {
        if(word.length() == index) {
            return letter + "\nDONE.";
        }
        String nextLetter = word.substring(index, index+1);
        return letter + "!!!\nGive me " + letter.toUpperCase() + "..." + getLetterClient(nextLetter).bounce(word, nextLetter, index+1);
    }

    private LetterClient getLetterClient(String letter) {
        switch (letter) {
            case "A":
                return letterAClient;    
            case "B":
                return letterBClient;    
            default:
                return letterAClient;        
        }
    }

}