/**
 *
 * Clase: ServidorHilo
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
package edu.cecar.servidor;

import edu.cecar.componentes.SocketServidor;
import edu.cecar.presentacion.MenuPrincipalServidor;
import static edu.cecar.presentacion.MenuPrincipalServidor.actializarDatosServidor;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Con está clase se despleja el servidor
 *
 */
final public class DesplegarServidorHilo extends Thread {

    private static SocketServidor socketServidor;

    // Constructor vacio
    public DesplegarServidorHilo() {
    }

    @Override
    public void run() {

        try {

            while (true) {

                // Se actualizan los datos en el servidor
                actializarDatosServidor.actualizarServidor();

                // Se abre el puerto TCP
                socketServidor = new SocketServidor(17020);

                // Se hace al servidor iterativo
                while (true) {

                    try {
                        // 
                        socketServidor.esperarConecxion();

                        // Información del cliente                
                        InetAddress clienteIp
                                = socketServidor.getSocket().getLocalAddress();

                        //Las conexiones para cada socket se administran sobre 
                        // un hilo diferente
                        ServidorHilo socketHilo
                                = new ServidorHilo(socketServidor.getEntrada(),
                                        socketServidor.getSalida(), clienteIp);
                        socketHilo.start();

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MenuPrincipalServidor.class.getName())
                                .log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(MenuPrincipalServidor.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }

                }

            }

        } catch (Exception e) {

            System.out.println("Servidor cerro la conexión");
        }

    }
}
