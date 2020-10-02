package edu.eci.arep.service;

import edu.eci.arep.model.Usuario;

/**
 * Interfaz que define un servicio de autenticación de usuarios
 * @author Julián Gutiérrez
 * @version 1.0
 */

public interface LoginService {

    /**
     * Metodo para autenticar un usuario
     * @param correo del usuario 
     * @param passwd del usuario
     * @return el usuario autenticado o null si el usuario no existe
     */
    public Usuario autenticar(String correo, String passwd);
    
}
