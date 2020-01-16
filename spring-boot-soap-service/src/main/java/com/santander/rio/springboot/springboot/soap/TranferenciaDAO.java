package com.santander.rio.springboot.springboot.soap;

import org.springframework.stereotype.Repository;

@Repository
public interface TranferenciaDAO {

     void registrarTransferencia(Transferencia transferencia);

     long consultarUltimoRegistro();

     void cancelarTransferencia(Long id);
}
