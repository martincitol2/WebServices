package com.santander.rio.springboot.springboot.rest;

import com.santander.rio.soapxml.Client;
import com.santander.rio.soapxml.ClientDetailsRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public String normalizate(String string, int tamanio, String refill, String letra) {
        if (letra.equals("R")) {
            String str = StringUtils.rightPad(string, tamanio, refill);
            str.substring(0, tamanio - 1);
            return str;
        } else {
            String str = StringUtils.leftPad(string, tamanio, refill);
            str.substring(0, tamanio - 1);
            return str;
        }
    }

    public Client validate(ClientDetailsRequest request) {

        Client client = new Client();
        client.setName(normalizate(request.getName(), 10, " ", "R"));
        client.setSurname(normalizate(request.getSurname(), 10, " ", "R"));
        client.setDni(normalizate(request.getDni(), 10, "0", "L"));
        client.setCbu(normalizate(request.getCbu(), 22, "0", "L"));
        client.setAccountNumber(normalizate(request.getAccountNumber(), 20, "0", "L"));
        client.setCurrency(normalizate(request.getCurrency(), 3, "0", "L"));
        client.setAmount(request.getAmount());

        return client;
    }
}
