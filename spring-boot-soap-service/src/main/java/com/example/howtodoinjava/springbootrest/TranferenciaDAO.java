package com.example.howtodoinjava.springbootrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class TranferenciaDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ClientDAO clientDAO;

    public String registrarTransferencia(Transferencia transferencia) {
        entityManager.createNamedStoredProcedureQuery("procedure-uno")
                .setParameter("fecha", transferencia.getFecha())
                .setParameter("monto", transferencia.getMonto())
                .setParameter("cbuLlegada", transferencia.getCbuEntrada())
                .setParameter("cbuSalida", transferencia.getCbuSalida())
                .setParameter("estado", transferencia.getEstado())
                .setParameter("imagen",transferencia.getImagen())
                .execute();
        return "Transferencia Exitosa";

    }

    public long consultarUltimoRegistro(){
         Transferencia transferencia = (Transferencia) entityManager.createNativeQuery("select * from Transferencia WHERE id=(SELECT max(id) FROM Transferencia )",Transferencia.class).getSingleResult();
         return transferencia.getId();
    }

    public String cancelarTransferencia(Long id) {
        Transferencia transfer = transferenciaRepository.getOne(id);
        String estado = "CANCELADO";
        clientDAO.realizarTransferencia(transfer);
        entityManager.createNamedStoredProcedureQuery("procedure-dos")
                .setParameter("id", id)
                .setParameter("estado", estado)
                .execute();

        return "Transaccion Exitosa";
    }
}
