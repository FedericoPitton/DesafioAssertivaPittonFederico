package com.example.desafioassertivapittonfederico.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsernameGeneratorService {
    public static final List<String> ColisionesArray = new ArrayList<>(Arrays.asList(
            "fggozalve", //nombre='Francisco', segundo nombre='Gonzalo', primer apellido='Gozalvez', y tipo de usuario='interno',
            "fggozalv1",
            "fggozalv2",
            "fggozalv3",
            "fggozalv4",
            "fggozalv5",
            "fggozalv6",
            "fggozalv7",
            "fggozalv8",
            "fggozalv9",
            "ex.capere", //nombre='Carlos', segundo nombre='Alberto', primer apellido='Perez', y tipo de usuario='externo',
            "ex.caper1",
            "ex.caper2",
            "ex.caper3",
            "ex.caper4",
            "ex.caper5",
            "ex.caper6",
            "ex.caper7",
            "ex.caper8",
            "ex.caper9",
            "ex.cape10",
            "ex.cape11",
            "ex.cape12",
            "ex.cape13"
    ));


    public String generateUsername(String firstName, String secondName, String lastName, String userType) throws Exception {
        String username;
        try {
            //creo el usuario con la primer letra del los nombres y el apellido completo
            username = firstName.substring(0, 1) + secondName.substring(0, 1) + lastName;
            //Si es un usuario externo adiciono ex. al inicio
            if (userType.equals("externo")) {
                username = "ex." + username;
            }
            //si el largo del usuario es mayor a 9 lo corto
            if (username.length() > 9) {
                username = username.substring(0, 9);
            }
            //Si el usuario nuevo creado existe llamo a la funcion containsUsername que creara un usuario valido
            if (ColisionesArray.contains(username)) username = containsUsername(username, 9, 10);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return username;
    }

    //Metodo recursivo que busca si existe el nombre de usuario con numeros adicionados
    //Si no existe lo crea y retorna
    private String containsUsername(String username, int sizeUsername, int forSize) throws Exception {
        //Si el username llegase a ser 0 o ex. quiere decir que se probaron todas las combinaciones
        if (username.length()==0 || username.equals("ex.")){
            throw new Exception("No se puedo crear usuario. No hay opciones disponibles");
        }
        //Creamos una base de usuario con una letra menos desde el final
        String baseUsername = username.substring(0, sizeUsername - 1);

        for (int i = forSize / 10; i < forSize; i++) {
            //Recorremos el bucle for adicionando un numero al final y corroborando si existe
            String auxUsername = baseUsername + i;
            //Si no existe retornamos este usuario ya que es valido
            if (!ColisionesArray.contains(auxUsername)) {
                return (auxUsername);
            }
        }
        //Si todas las opciones existian volvemos a llamar al metodo enviando la base con la letra cortada
        //reduciendo el tamaño en 1 y multiplicando el forsize por si mismo. Esto para equiparar el espacio de la
        //letra removida
        username = containsUsername(baseUsername, sizeUsername - 1, forSize * forSize);
        return username;
    }

}
