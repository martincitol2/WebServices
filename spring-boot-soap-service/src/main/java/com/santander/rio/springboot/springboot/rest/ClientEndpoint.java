package com.santander.rio.springboot.springboot.rest;

import com.santander.rio.soapxml.Client;
import com.santander.rio.soapxml.ClientDetailsRequest;
import com.santander.rio.soapxml.ClientDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEndpoint {
    private static final String NAMESPACE_URI = "http://www.santander.com.ar";

    @Autowired
    Validation validation;

    @Autowired
    EnviarMensaje enviarMensaje;

    private ClientRepository ClientRepository;

    @Autowired
    public ClientEndpoint(ClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ClientDetailsRequest")
    @ResponsePayload
    public ClientDetailsResponse getClient(@RequestPayload ClientDetailsRequest request) {

        Client client = validation.validate(request);
        return enviarMensaje.enviarMensaje(client);

    }
}