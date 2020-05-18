/**
 *
 * Clase: SocketCliente
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Está clase crea un conexión TCP hacia un servidor
 *
 */
public class SocketCliente {

    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    /**
     *
     * @param direccionIp
     * @param numeroPuertoTCP
     */
    public SocketCliente(String direccionIp, int numeroPuertoTCP) {

        try {
            // Se crea la petición de un socket
            socket = new Socket(direccionIp, numeroPuertoTCP);

            // Se administran las entradas y salidas            
            // Salida
            salida = new ObjectOutputStream(socket.getOutputStream());
            // Entrada
            entrada = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    // Se crea un método para cerrar la conexión
    public void cerrarConexion() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
