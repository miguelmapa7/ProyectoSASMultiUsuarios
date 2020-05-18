/**
 *
 * Clase: ServidorCliente
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
package edu.cecar.cliente;

import edu.cecar.componentes.SocketCliente;
import java.io.IOException;

/**
 * Esta clase representa al cliente y envia peticiones de servicio al servidor
 *
 */
public class ServidorCliente {

    private final SocketCliente socketCliente;
    
    /**
     * 
     * @param ip
     * @throws IOException 
     */
    public ServidorCliente(String ip) throws IOException {
        socketCliente = new SocketCliente(ip, 17020);
    }

    // Se crea método para recuperar el atributo de conexión
    public SocketCliente getSocketCliente() {
        return socketCliente;
    }

}
