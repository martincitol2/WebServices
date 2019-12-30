package com.example.howtodoinjava.springbootsoapservice;

import com.howtodoinjava.xml.school.Client;
import com.howtodoinjava.xml.school.ClientDetailsRequest;
import com.howtodoinjava.xml.school.ClientDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEndpoint {
    private static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";

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