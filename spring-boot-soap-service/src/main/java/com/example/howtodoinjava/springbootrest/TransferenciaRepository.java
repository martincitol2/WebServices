package com.example.howtodoinjava.springbootrest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}