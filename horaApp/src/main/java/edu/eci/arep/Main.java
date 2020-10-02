package edu.eci.arep;

import spark.Service;
import spark.staticfiles.StaticFilesConfiguration;
import java.io.FileReader;
import java.util.Properties;

import java.time.LocalDateTime;


/**
 * Clase principal cuya tarea es arrancar un servidor HTTP 
 * @author Julián Gutiérrez
 * @version 1.0
 */
public class Main {
	
	
	/**
	*inicializa spark 
	*/
    public static void main(String... args){
		Properties login = new Properties();
		try (FileReader in = new FileReader("src/main/resources/login.properties")) {
			login.load(in);
		} catch (Exception e) {}
		String keystore = login.getProperty("ks");
		Service service = Service.ignite();
		service.secure("keystore/ecikeystore.p12", keystore, null, null);
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
		service.get("/getHora" , (req,res) ->{
			int hora = LocalDateTime.now().getHour();
			int min = LocalDateTime.now().getMinute();
			String tiempo = hora + ":" + min;
			return tiempo;
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
        return 5001;
    }
    
}