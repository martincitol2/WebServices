package com.example.howtodoinjava.springbootrest;

import com.example.howtodoinjava.SpringBootSoapServiceApplication;
import com.howtodoinjava.xml.school.ClientDetailsResponse;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.io.File;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SpringBootSoapServiceApplication.class);

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ClientRepositorio clientRepository;

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    TranferenciaDAO tranferenciaDAO;

    @Autowired
    EntityManager entityManager;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }
    @PutMapping(path = "/add/transfer/pending")
    public @ResponseBody
    Object addTransferPending(@RequestBody Transferencia transferencia)  {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");
        String est = "Pendiente";

        try {

            tranferenciaDAO.registrarTransferencia(transferencia);
            long id = tranferenciaDAO.consultarUltimoRegistro();
            File file = new File("C:\\aplicaciones\\transferencias\\imagenes\\recibidas",id+"-"+transferencia.getCbuSalida()+"-"+transferencia.getCbuEntrada()+".jpg");
            file.createNewFile();
        } catch (Exception e) {
            logger.error("no se pudo realizar la transferencia debido a: " + e.getMessage());
            response.setDescripcion("No se pudo Realizar la transferencia");
            response.setCodigoError("500");
        }


        return response;
    }

    @GetMapping(path="/transferencia")
    public @ResponseBody Object getTransferencia(){
      Transferencia transferencia = (Transferencia) entityManager.createNativeQuery("select * from Transferencia where id=1",Transferencia.class).getSingleResult();
      return transferencia;
    }

/*
    @PutMapping(path = "/add/transfer")
    public @ResponseBody
    Object addTransfer(@RequestParam String cbu, @RequestParam String cbu2, @RequestParam Double amount) {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");
        String est = "Transferido";
        try {
            edao.realizarTransferencia(cbu, cbu2,amount);
            tdao.registrarTransferencia(new Date(), amount, cbu, cbu2, est);

        } catch (Exception e) {
            logger.error("no se pudo realizar la transferencia debido a: " + e.getMessage());
            response.setDescripcion("No se pudo Realizar la transferencia");
            response.setCodigoError("500");
        }

        return response;
    }
*/

    @PutMapping(path = "/cancel/transfer")
    public @ResponseBody
    Object cancelTransfer(@RequestParam Long id) {
        ClientDetailsResponse response = new ClientDetailsResponse();
        response.setDescripcion("Todo OK");
        response.setCodigoError("200");

        try {
            tranferenciaDAO.cancelarTransferencia(id);

        } catch (Exception e) {
            logger.error("no se pudo cancelar la transferencia debido a: " + e.getMessage());
            response.setDescripcion("No se pudo Cancelar la transferencia");
            response.setCodigoError("500");
        }

        return response;
    }

    @GetMapping(path = "/clients")
    public @ResponseBody
    Iterable<Client> getClientes() {

        return clientRepository.findAll();

    }

    @GetMapping(value = "/getall")
    public @ResponseBody
    Iterable<Client> getAll() {
        return clientDAO.getAllClients();
    }

    @GetMapping(path = "/client")
    public @ResponseBody
    Object getCliente(@RequestParam String cbu) {

        try {
            if (!clientRepository.findByCbu(cbu).equals(null)) {
                return clientRepository.findByCbu(cbu);
            } else {
                return "Cliente no Encontrado";
            }
        } catch (Exception e) {
            return "El CBU ingresado no pertenece a ningún cliente";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userRepository.findAll();

    }
}