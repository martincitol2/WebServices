package com.santander.rio.springboot.springboot.soap;

import com.santander.rio.SpringBootServiceApplication;
import com.santander.rio.soapxml.ClientDetailsResponse;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.io.File;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SpringBootServiceApplication.class);

    @Autowired
    private ClientRepositorio clientRepository;

    @Autowired
    TranferenciaDAO tranferenciaDAO;

    @Autowired
    EntityManager entityManager;

    @PutMapping(path = "/add/transfer/pending")
    public @ResponseBody
    Object addTransferPending(@RequestBody Transferencia transferencia) {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");

        try {
            tranferenciaDAO.registrarTransferencia(transferencia);
            long id = tranferenciaDAO.consultarUltimoRegistro();
            File file = new File("C:\\aplicaciones\\transferencias\\imagenes\\recibidas", id + "-" + transferencia.getCbuSalida() + "-" + transferencia.getCbuEntrada() + ".jpg");
            file.createNewFile();
            logger.info("Se agrego transferencia pendiente");
        } catch (Exception e) {
            logger.error("no se pudo agregar la transferencia pendiente debido a: " + e.getMessage());
            response.setDescripcion("No se pudo Realizar la transferencia");
            response.setCodigoError("500");
        }

        return response;
    }

    @GetMapping(path = "/transferencia")
    public @ResponseBody
    Object getTransferencia() {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");

        try {
            Transferencia transferencia = (Transferencia) entityManager.createNativeQuery("select * from Transferencia where id=1", Transferencia.class).getSingleResult();
            logger.info("Se mostro exitosamente las transferencias");
            return transferencia;
        } catch (Exception e) {
            logger.error("No se pudo devolver transferencias");
            return response;
        }
    }

    @PutMapping(path = "/cancel/transfer")
    public @ResponseBody
    Object cancelTransfer(@RequestParam Long id) {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");

        try {
            tranferenciaDAO.cancelarTransferencia(id);
            logger.info("Todo Ok, Transferencia por cancelar agregada");
        } catch (Exception e) {
            logger.error("no se pudo cancelar la transferencia debido a: " + e.getMessage());
            response.setDescripcion("No se pudo Cancelar la transferencia");
            response.setCodigoError("500");
        }

        return response;
    }

    @GetMapping(path = "/client")
    public @ResponseBody
    Object getCliente(@RequestParam String cbu) {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");

        try {
            if (!clientRepository.findByCbu(cbu).equals(null)) {
                logger.info("Buscando cliente por cbu");
                return clientRepository.findByCbu(cbu);
            }
        } catch (Exception e) {
            logger.error("Error al buscar el cliente: "+e.getMessage());
            response.setDescripcion("El CBU ingresado no pertenece a ningún cliente");
            response.setCodigoError("404");
        }
        return response;
    }
}