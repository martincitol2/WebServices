package com.example.howtodoinjava.springbootrest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public interface ClientRepositorio extends JpaRepository<Client, Long> {

    Client findByCbu(@Param("cbu") String cbu);
}