package com.example.howtodoinjava.springbootrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;

@Repository
public class TranferenciaDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ClientDAO clientDAO;

    public String registrarTransferencia(Date fecha, Double monto, String cbuSalida, String cbuLlegada, String estado) {
        entityManager.createNamedStoredProcedureQuery("procedure-uno")
                .setParameter("fecha", fecha)
                .setParameter("monto", monto)
                .setParameter("cbuLlegada", cbuLlegada)
                .setParameter("cbuSalida", cbuSalida)
                .setParameter("estado", estado)
                .execute();
        return "Transaccion Exitosa";

    }

    public String cancelarTransferencia(Long id) {
        Transferencia transfer = transferenciaRepository.getOne(id);
        Date fecha = transfer.getFecha();
        Double monto = transfer.getMonto();
        String cbuSalida = transfer.getCbuSalida();
        String cbuLlegada = transfer.getCbuEntrada();
        String estado = "CANCELADO";
        clientDAO.realizarTransferencia(cbuSalida,cbuLlegada, monto);
        entityManager.createNamedStoredProcedureQuery("procedure-dos")
                .setParameter("id", id)
                .setParameter("estado", estado)
                .execute();

        return "Transaccion Exitosa";
    }
}
