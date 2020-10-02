package edu.eci.arep.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.eci.arep.model.Usuario;
import edu.eci.arep.service.LoginService;

/**
 * Clase que define un servicio de autenticación de usuarios
 * @author Julián Gutiérrez
 * @version 1.0
 */

public class LoginServiceInMemory implements LoginService{

    private List<Usuario> usuarios = new ArrayList<Usuario>(); 

    /**
     * Constructor de la clase que inicializa dos usuarios
     */
    public LoginServiceInMemory(){
        Usuario u = new Usuario ("Pruebastian R.",  "pruebastian@mail.com", "e83e8535d6f689493e5819bd60aa3e5fdcba940e6d111ab6fb5c34f24f86496bf3726e2bf4ec59d6d2f5a2aeb1e4f103283e7d64e4f49c03b4c4725cb361e773");
        this.usuarios.add(u);
        Usuario u2 = new Usuario ("Pruebastina M.",  "pruebastina@otromail.com", "9fe0f49b15a4927de43ee0a431034f83b43f6382a19f2c7f8b8757284d62c339766b6a44d3c83eed09bc15f7e6693e866bfc8aa554f88a94a5040884350df208");
        this.usuarios.add(u2);
    }

    /**
     * Metodo para autenticar un usuario
     * @param correo del usuario 
     * @param passwd del usuario
     * @return el usuario autenticado o null si el usuario no existe
     */
    @Override
    public Usuario autenticar(String correo, String passwd) {
        Usuario u = null;
        int pos = -1; 
        for (int i = 0; i < this.usuarios.size(); i++){
            if ((this.usuarios.get(i).getCorreo().equals(correo)) && (this.usuarios.get(i).getPasswd().equals(passwd))){
                pos = i; 
                break;
            }
        }
        if (pos > -1){
            u = usuarios.get(pos);
        }
        return u;
    }




    
}
