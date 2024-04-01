package com.shirodkar.wordmesh.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri="http://letter-a:8080")
public interface LetterAClient extends LetterClient {
}