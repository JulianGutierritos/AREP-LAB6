package edu.eci.arep;

import org.junit.Test;

import edu.eci.arep.model.Usuario;
import edu.eci.arep.service.LoginService;
import edu.eci.arep.service.impl.LoginServiceInMemory;
import junit.framework.Assert;

/**
 * Clase de pruebas
 * @author Julian Gutierrez
 * @version 1.0
 */
public class LoginServiceTest {

    private LoginService lserv = new LoginServiceInMemory();

    @Test
    public void deberiaAutenticarUsuarioExistente(){
        Usuario u = lserv.autenticar("pruebastian@mail.com", "e83e8535d6f689493e5819bd60aa3e5fdcba940e6d111ab6fb5c34f24f86496bf3726e2bf4ec59d6d2f5a2aeb1e4f103283e7d64e4f49c03b4c4725cb361e773");
        Assert.assertNotNull(u);
    }

    @Test
    public void deberiaAutenticarUsuarioExistente2(){
        Usuario u = lserv.autenticar("pruebastina@otromail.com", "9fe0f49b15a4927de43ee0a431034f83b43f6382a19f2c7f8b8757284d62c339766b6a44d3c83eed09bc15f7e6693e866bfc8aa554f88a94a5040884350df208");
        Assert.assertNotNull(u);
    }

    @Test
    public void noDeberiaAutenticarUsuarioExistente(){
        Usuario u = lserv.autenticar("pruebastian@mail.com", "contraseñamala");
        Assert.assertNull(u);
    }

    @Test
    public void noDeberiaAutenticarUsuarioExistente2(){
        Usuario u = lserv.autenticar("pruebastina@otromail.com", "contraseñamala");
        Assert.assertNull(u);
    }
    
}
