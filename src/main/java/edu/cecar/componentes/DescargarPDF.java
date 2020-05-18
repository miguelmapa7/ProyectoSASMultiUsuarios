/**
 *
 * Clase: DescargarPDF
 *
 * @version: 0.1
 *
 * @Fecha de creación: 16/05/2020
 *
 * Fecha de Modificación:
 *
 * @author migue 1102838994
 *
 * Copyrigth: CECAR
 *
 */
package edu.cecar.componentes;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Está clase permite descargar el Archivo pdf dedes la URL
 *
 */
public class DescargarPDF {

    // Se valida la conexión    
    private String conexion;

    /**
     *
     * @param url Url que contiene el archivo a descargar
     * @throws MalformedURLException
     * @throws IOException
     */
    public DescargarPDF(String url) throws MalformedURLException, IOException {

        try {
            // Se instancia un objeto URL
            URL url1 = new URL(url);

            // Se prueba si hay conexión
            URLConnection conn = url1.openConnection();
            conn.connect();

            InputStream in = url1.openStream();
            Files.copy(in, Paths.get("RecursosServidor/archivoPdf.pdf"),
                    StandardCopyOption.REPLACE_EXISTING);
            in.close();

            // Se valida que el archivo ha sido descargado de manera exitosa
            conexion = "true";

        } catch (MalformedURLException e) {
            // La url tiene mal formato
            conexion = "falso";
        } catch (IOException e) {
            // La url no conecta
            conexion = "falso";
        }
    }

    public String getConexion() {
        return conexion;
    }
}
