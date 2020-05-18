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

import edu.cecar.ApachePOI.ConectarApachePOI;
import edu.cecar.componentes.PdfBoxExtraerTexto;
import edu.cecar.modelo.ObjetoFTP;
import static edu.cecar.presentacion.MenuPrincipalServidor.actializarDatosServidor;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Con está clase se manejan los diferentes usuarios que van llegando
 *
 */
final public class ServidorHilo extends Thread {

    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private InetAddress clienteIp;

    /**
     *
     * @param entrada
     * @param salida
     * @param clienteIp
     */
    public ServidorHilo(ObjectInputStream entrada,
            ObjectOutputStream salida, InetAddress clienteIp) {
        this.entrada = entrada;
        this.salida = salida;
        this.clienteIp = clienteIp;
    }

    @Override
    public void run() {

        try {

            // Se actualizan los datos en el servidor de con respecto a la 
            // conexión de clientes
            int ipConectadas = actializarDatosServidor.getClienteConectados();
            int nuevoIpConectadas = ipConectadas + 1;

            int ipDesconectadas = actializarDatosServidor
                    .getClientesDesconectados();

            int neevoTotalIp = ipDesconectadas + nuevoIpConectadas;

            actializarDatosServidor.setClienteConectados(nuevoIpConectadas);
            actializarDatosServidor.setClientesTotal(neevoTotalIp);

            // Se guardan las Ips conectadas
            ArrayList<InetAddress> listadoIpConectadas
                    = new ArrayList<InetAddress>();
            listadoIpConectadas = actializarDatosServidor.getIpConectadas();
            listadoIpConectadas.add(clienteIp);
            actializarDatosServidor.setIpConectadas(listadoIpConectadas);
            actializarDatosServidor.actualizarServidor();

            while (true) {

                // Se recibe petición del cliente
                Object object = entrada.readObject();

                // Se crea un objeto de tipo ObjectFTP y se le asigna el objeto 
                // object que representa la petición del cliente
                ObjetoFTP objetoFTP = (ObjetoFTP) object;

                // Se procesde a escrapear el pdf descargado
                // Se crea un Array List para guardar el texto obtenido                        
                ArrayList<String> textoTotalGenerado
                        = new ArrayList<String>();

                // Se crea un objeto de clase que escrapea el documento
                PdfBoxExtraerTexto bet = new PdfBoxExtraerTexto();

                // Se obtiene el texto y se guarda en el array
                textoTotalGenerado = bet.getTextoTotalGenerado();

                // Se procede a generar el documento .doc
                // Se pasa por parametros el array de texto obtenido
                ConectarApachePOI conectarApachePOI
                        = new ConectarApachePOI();

                conectarApachePOI.generarArchivoDoc(textoTotalGenerado);

                File file = new File("RecursosServidor/"
                        + objetoFTP.getNombreArchivo());

                FileInputStream fileInputStream = new FileInputStream(file);

                byte[] bytes = new byte[(int) file.length()];

                int numerosBytesLeidos = fileInputStream.read(bytes);

                fileInputStream.close();

                // Se le pasan los byte al objeto
                objetoFTP.setBytesArchivo(bytes);

                // Se envía el objeto al cliente
                salida.writeObject(objetoFTP);

            }

        } catch (Exception e) {

            // Actualizar datos del servidor
            int ipDesconectadas = actializarDatosServidor
                    .getClientesDesconectados();
            int nueevoIpDesconectadas = ipDesconectadas + 1;

            int ipConectadas = actializarDatosServidor.getClienteConectados();
            int nuevoIpConectadas = ipConectadas - 1;

            int totalIpConectadas = actializarDatosServidor.getClientesTotal();
            totalIpConectadas = nueevoIpDesconectadas + nuevoIpConectadas;

            actializarDatosServidor.setClienteConectados(nuevoIpConectadas);
            actializarDatosServidor
                    .setClientesDesconectados(nueevoIpDesconectadas);
            actializarDatosServidor.setClientesTotal(totalIpConectadas);

            // Se guardan las Ips conectadas
            ArrayList<InetAddress> listadoIpDesConectadas = new ArrayList<>();
            listadoIpDesConectadas
                    = actializarDatosServidor.getIpDesconectadas();
            listadoIpDesConectadas.add(clienteIp);
            actializarDatosServidor.setIpDesconectadas(listadoIpDesConectadas);
            actializarDatosServidor.actualizarServidor();

            System.out.println("Cliente cerro la conexión");
        }

    }
}
