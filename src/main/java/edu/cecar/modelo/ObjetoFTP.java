/**
 *
 * Clase: ObjetoFTP
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
package edu.cecar.modelo;

import java.io.Serializable;

/**
 * Está clase modela el objeto que se envia por el socket
 *
 */
public class ObjetoFTP implements Serializable {

    private static final long serialVersionUID = 103L;
    private String nombreArchivo;
    private byte[] bytesArchivo;

    public ObjetoFTP(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public byte[] getBytesArchivo() {
        return bytesArchivo;
    }

    public void setBytesArchivo(byte[] bytesArchivo) {
        this.bytesArchivo = bytesArchivo;
    }

}
