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
    public String realizarTransferencia(Transferencia transferencia) {
        entityManager.createNamedStoredProcedureQuery("procedure-two")
                .setParameter("cbu_llegada", transferencia.getCbuSalida())
                .setParameter("cbu_salida", transferencia.getCbuEntrada())
                .setParameter("importe", transferencia.getMonto())
                .execute();
        return "Transaccion Exitosa";

    }

}
