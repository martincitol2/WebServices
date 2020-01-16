package com.santander.rio.springboot.springboot.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class TransferenciaDAOImpl implements TranferenciaDAO {

    @Autowired
    private EntityManager entityManager;

    public void registrarTransferencia(Transferencia transferencia) {
        entityManager.createNamedStoredProcedureQuery("procedure-uno")
                .setParameter("fecha", transferencia.getFecha())
                .setParameter("monto", transferencia.getMonto())
                .setParameter("cbuLlegada", transferencia.getCbuEntrada())
                .setParameter("cbuSalida", transferencia.getCbuSalida())
                .setParameter("estado", transferencia.getEstado())
                .setParameter("imagen",transferencia.getImagen())
                .execute();

    }

    public long consultarUltimoRegistro(){
        Transferencia transferencia = (Transferencia) entityManager.createNativeQuery("select * from Transferencia WHERE id=(SELECT max(id) FROM Transferencia )",Transferencia.class).getSingleResult();
        return transferencia.getId();
    }

    public void cancelarTransferencia(Long id) {
        String estado = "POR CANCELAR";
        entityManager.createNamedStoredProcedureQuery("procedure-dos")
                .setParameter("id", id)
                .setParameter("estado", estado)
                .execute();
    }
}
