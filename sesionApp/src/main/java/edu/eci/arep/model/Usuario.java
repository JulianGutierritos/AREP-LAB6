package edu.eci.arep.model;

public class Usuario {

    private String nombre; 
    private String correo; 
    private String passwd;
    
    /**
     * Constructor del usuario 
     * @param nombre del usuario
     * @param correo del usuario
     * @param passwd del usuario
     */
    public Usuario(String nombre, String correo, String passwd){
        this.nombre = nombre;
        this.correo = correo; 
        this.passwd = passwd; 
    }

    /**
     * Constructor del usuario
     */
    public Usuario(){
    }

    /**
     * Retorna el nombre del usuario
     * @return String nombre del usuario
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Retorna el correo del usuario
     * @return String correo del usuario
     */
    public String getCorreo(){
        return this.correo;
    }

    /**
     * Retorna el passwd del usuario
     * @return String passwd del usuario
     */
    public String getPasswd(){
        return this.passwd;
    }

    /**
     * Cambia el nombre del usuario
     * @param nombre: nuevo nombre del usuario
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    /**
     * Cambia el correo del usuario
     * @param correo: nuevo correo del usuario
     */
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    /**
     * Cambia el passwd del usuario
     * @param passwd: nuevo passwd del usuario
     */
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }

    /**
     * Define como el usuario va a ser transformado en cadena 
     * @return String con la representacion del usuario como cadena
     */
    @Override
	public String toString() {
		return  "{" + "\"Nombre\": \"" + this.nombre + "\" , \"Correo\": \"" +  this.correo + "\" , \"Passwd\": \"" + this.passwd + "\"  }";

    }
    
}
