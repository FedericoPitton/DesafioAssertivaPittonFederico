package com.example.desafioassertivapittonfederico.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UsernameGeneratorServiceTest {

    private UsernameGeneratorService usernameGeneratorService;
    @BeforeEach
    void setUp() {
        usernameGeneratorService = new UsernameGeneratorService();
    }

    @Test
    void generateUsername() throws Exception{
        String firstName = "Usuario";
        String secondName = "De";
        String lastName = "Testeo";
        String userType = "interno";

        String username = usernameGeneratorService.generateUsername(firstName, secondName, lastName, userType);

        Assertions.assertEquals("udtesteo", username);
        userType = "externo";
        username = usernameGeneratorService.generateUsername(firstName, secondName, lastName, userType);

        Assertions.assertEquals("ex.udtest", username);
    }
    @Test
    void generateUsername_collision() throws Exception{
        String firstName = "Francisco";
        String secondName = "Gonzalo";
        String lastName = "Gozalvez";
        String userType = "interno";

        String username = usernameGeneratorService.generateUsername(firstName, secondName, lastName, userType);

        Assertions.assertEquals("fggozal10", username);

        firstName = "Carlos";
        secondName = "Alberto";
        lastName = "Perez";
        userType = "externo";

        username = usernameGeneratorService.generateUsername(firstName, secondName, lastName, userType);

        Assertions.assertEquals("ex.cape14", username);
    }

    @Test
    public void containsUsername_NoOption() {
        String username = "ex.";

        Assertions.assertThrows(Exception.class, () -> {
            usernameGeneratorService.containsUsername(username, 9, 10);
        });
    }

    @Test
    public void invalidParameters() {
        String firstName = "";
        String secondName = "Gonzalo";
        String lastName = "Gozalvez";
        String userType = "interno";

        Assertions.assertThrows(Exception.class, () -> {
            usernameGeneratorService.validateParameters(firstName, secondName, lastName, userType);
        });
    }

    @Test
    public void invalidUserType() {
        String firstName = "Francisco";
        String secondName = "Gonzalo";
        String lastName = "Gozalvez";
        String userType = "invalido";

        Assertions.assertThrows(Exception.class, () -> {
            usernameGeneratorService.validateParameters(firstName, secondName, lastName, userType);
        });
    }
}