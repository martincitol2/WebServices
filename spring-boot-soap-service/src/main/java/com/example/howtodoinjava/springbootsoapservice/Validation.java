package com.example.howtodoinjava.springbootsoapservice;

import com.howtodoinjava.xml.school.Client;
import com.howtodoinjava.xml.school.ClientDetailsRequest;
import com.howtodoinjava.xml.school.ClientDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public Client validate(ClientDetailsRequest request) {

        Client client = new Client();
        client.setName(request.getName());
        client.setSurname(request.getSurname());
        client.setDni(request.getDni());
        client.setNup(request.getNup());
        client.setCbu(request.getCbu());
        client.setAccountNumber(request.getAccountNumber());
        client.setCurrency(request.getCurrency());
        client.setAmount(request.getAmount());

        return client;
    }
}
