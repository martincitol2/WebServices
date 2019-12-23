package com.example.howtodoinjava.springbootrest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositorio extends JpaRepository<Client, Integer> {

    Client findByCbu(String cbu);
}