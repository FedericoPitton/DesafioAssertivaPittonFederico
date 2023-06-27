package com.example.desafioassertivapittonfederico.controller;

import com.example.desafioassertivapittonfederico.service.UsernameGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class UsernameGeneratorController {
    private final UsernameGeneratorService usernameGeneratorService;

    public UsernameGeneratorController(UsernameGeneratorService usernameGeneratorService) {
        this.usernameGeneratorService = usernameGeneratorService;
    }

    @PostMapping("/generateUsername")
    public ResponseEntity<String> generateUsername(
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String lastName,
            @RequestParam String userType
    ) {

        try {
            //Valido que los parametros sean correctos
            usernameGeneratorService.validateParameters(firstName, secondName, lastName, userType);
            //Guardo en una variable el usuario generado a traves del servicio generador de usuarios
            String generatedUsername = usernameGeneratorService
                    .generateUsername(
                            firstName,
                            secondName,
                            lastName,
                            userType);

            return ResponseEntity.ok(generatedUsername);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
