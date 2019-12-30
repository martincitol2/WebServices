package com.example.howtodoinjava.springbootrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ClientDAO {

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Iterable<Client> getAllClients() {
        return entityManager.createNamedStoredProcedureQuery("procedure-one").getResultList();
    }

    @SuppressWarnings("unchecked")
    public String realizarTransferencia(String cbu, String cbu2, Double amount) {
        entityManager.createNamedStoredProcedureQuery("procedure-two")
                .setParameter("cbu_llegada", cbu)
                .setParameter("cbu_salida", cbu2)
                .setParameter("importe", amount)
                .execute();
        return "Transaccion Exitosa";

    }

}
