package com.santander.rio.springboot.springboot.rest;

import java.util.HashMap;
import java.util.Map;
import com.santander.rio.soapxml.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientRepository {
    private static final Map<String, Client> clients = new HashMap<>();

}