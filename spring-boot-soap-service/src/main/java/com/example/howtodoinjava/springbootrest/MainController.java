package com.example.howtodoinjava.springbootrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ClientRepositorio clientRepository;

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

    @PutMapping(path = "/add/transfer")
    public @ResponseBody
    String addTransfer(@RequestParam String name, @RequestParam String surname, @RequestParam String cbu, @RequestParam String dni, @RequestParam Double amount) {

        return "Transferido";
    }

    @GetMapping(path = "get/client")
    public @ResponseBody
    Client getClient(@RequestParam String cbu){
        return clientRepository.findByCbu(cbu);

    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userRepository.findAll();

    }
}