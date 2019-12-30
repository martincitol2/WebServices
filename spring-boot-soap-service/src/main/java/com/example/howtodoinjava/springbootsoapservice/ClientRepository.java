package com.example.howtodoinjava.springbootsoapservice;

import java.util.HashMap;
import java.util.Map;
import com.howtodoinjava.xml.school.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientRepository {
    private static final Map<String, Client> clients = new HashMap<>();

}