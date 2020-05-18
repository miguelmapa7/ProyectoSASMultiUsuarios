/**
 *
 * Clase: SocketServidor
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase permite crear un puerto TCP y configuar los puertos de entrada y
 * salida del Socket
 *
 */
public class SocketServidor {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    /**
     *
     * @param numeroPuertoTCP
     */
    public SocketServidor(int numeroPuertoTCP) {

        try {
            // Se instancia el servidor
            serverSocket = new ServerSocket(numeroPuertoTCP);

        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @throws Exception
     */
    public void esperarConecxion() throws Exception {
        // Se recibe la perición de un Socket
        socket = serverSocket.accept();

        // Se configuran los canales de entrada y salida            
        // Salida
        salida = new ObjectOutputStream(socket.getOutputStream());
        // Entrada
        entrada = new ObjectInputStream(socket.getInputStream());

    }

    public ServerSocket getServerSocket() {
        return serverSocket;
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

    public void cerrarSocket() {
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
