package com.example.howtodoinjava.springbootsoapservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.howtodoinjava.xml.school.Client;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.howtodoinjava.xml.school.Student;

@Component
public class ClientRepository {
    private static final Map<String, Client> clients = new HashMap<>();

}