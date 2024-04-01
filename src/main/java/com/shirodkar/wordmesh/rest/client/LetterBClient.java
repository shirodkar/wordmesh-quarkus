package com.shirodkar.wordmesh.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri="http://letter-b:8080")
public interface LetterBClient extends LetterClient {
}