package edu.eci.arep;

import spark.Service;
import spark.staticfiles.StaticFilesConfiguration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Properties;

import edu.eci.arep.model.Usuario;
import edu.eci.arep.service.LoginService;
import edu.eci.arep.service.impl.LoginServiceInMemory;

/**
 * Clase principal cuya tarea es arrancar un servidor HTTP
 * 
 * @author Julián Gutiérrez
 * @version 1.0
 */
public class Main {

	/**
	 * inicializa spark
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String... args) {
		LoginService lserv = new LoginServiceInMemory();
		Service service = Service.ignite();
		Properties login = new Properties();
		try (FileReader in = new FileReader("src/main/resources/login.properties")) {
			login.load(in);
		} catch (Exception e) { }
		String keystore = login.getProperty("ks");
		String truststore = login.getProperty("ts");
		service.secure("keystore/ecikeystore.p12", keystore, "keystore/myTrustStore", truststore);
		service.port(getPort());
		service.before("/usuario.html" , (req , res) ->{
			if (req.session().attribute("usuario") == null){
				service.halt(401, "Go Away!");
			}
		});
		StaticFilesConfiguration staticHandler = new StaticFilesConfiguration();
		staticHandler.configure("/static");
		service.before((request, response) ->
                staticHandler.consume(request.raw(), response.raw()));
		
		service.post("/autenticar", (req, res) -> {
			res.type("application/json");
			String body[] = (req.body().replace("{","").replace("}", "").replace("\"", "").split(","));
			String correo = (body[0].split(":"))[1];
			String passwd = (body[1].split(":"))[1];
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    	digest.reset();
	    	digest.update(passwd.getBytes("utf8"));
			String hash = String.format("%0128x", new BigInteger(1, digest.digest()));
			Usuario u = lserv.autenticar(correo, hash);
			if (u != null){
				req.session().attribute("usuario", u);
			}
			return u.toString();
		});

		service.post("/autenticar", (req, res) -> {
			res.type("application/json");
			String body[] = (req.body().replace("{","").replace("}", "").replace("\"", "").split(","));
			String correo = (body[0].split(":"))[1];
			String passwd = (body[1].split(":"))[1];
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    	digest.reset();
	    	digest.update(passwd.getBytes("utf8"));
			String hash = String.format("%0128x", new BigInteger(1, digest.digest()));
			Usuario u = lserv.autenticar(correo, hash);
			if (u != null){
				req.session().attribute("usuario", u);
			}
			return u.toString();
		});
		service.get("/getNombre" , (req,res) -> {
			String nombre = null;
			if (req.session().attribute("usuario") != null){
				nombre = ((Usuario) req.session().attribute("usuario")).getNombre();
			}
			return nombre;
		});
		service.get("/logOut" , (req,res) -> {
			req.session().removeAttribute("usuario");
			return "ok";
		});
		service.get("/getHora" , (req,res) ->{
			String urls = "https://localhost:5001/getHora";
			return URLSecureReader.readURLS(urls);
		});

	}
	

	/**
	* Cambia el puerto 
	* @return puerto de la aplicacion
	*/
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
    
}