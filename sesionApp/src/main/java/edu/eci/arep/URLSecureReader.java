package edu.eci.arep;


import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Clase para leer paginas web mediante el protocolo https
 * @author Julián Gutiérrez
 * @version 1.0
 */


public class URLSecureReader {

    /**
     * Lee una url con https 
     * @param urls url que se va a leer
     * @return String con el cuerpo de la pagina leida
     */
    public static String readURLS(String urls) {
        String inputLine = "";
        try {
            File trustStoreFile = new File("keystore/myTrustStore");
            char[] trustStorePassword = "123456".toCharArray();
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);
            for(TrustManager t: tmf.getTrustManagers()){
                System.out.println(t);
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
            URL url = new URL(urls);
            BufferedReader reader= new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = reader.readLine();   
        
        } catch (KeyStoreException ex) {
            System.out.println("KeyStoreException");
            Logger.getLogger(URLSecureReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
            Logger.getLogger(URLSecureReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("IOException");
            Logger.getLogger(URLSecureReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("NoSuchAlgorithmException");
            Logger.getLogger(URLSecureReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            System.out.println("CertificateException");
            Logger.getLogger(URLSecureReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            System.out.println("KeyManagementException");
            Logger.getLogger(URLSecureReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputLine;

    }

}
