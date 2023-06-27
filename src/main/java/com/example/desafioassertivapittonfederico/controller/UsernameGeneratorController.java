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
            //Valido que no vengan vacios los parametros
            if (firstName.isEmpty() || secondName.isEmpty() || lastName.isEmpty() || userType.isEmpty()) {
                throw new Exception("Todos los parámetros deben ser proporcionados");
            }
            //Los convierto a minuscula y paso al servicio generador de usuario
            String lowerFirstName = firstName.toLowerCase();
            String lowerSecondName = secondName.toLowerCase();
            String lowerLastName = lastName.toLowerCase();
            String lowerUserType = userType.toLowerCase();

            String generatedUsername = usernameGeneratorService
                    .generateUsername(
                            lowerFirstName,
                            lowerSecondName,
                            lowerLastName,
                            lowerUserType);

            return ResponseEntity.ok(generatedUsername);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }


    }
}
