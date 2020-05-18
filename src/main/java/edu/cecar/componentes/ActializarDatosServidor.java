/**
 *
 * Clase: ActializarDatosServidor
 *
 * @version: 0.1
 *
 * @Fecha de creación: 17/05/2020
 *
 * Fecha de Modificación:
 *
 * @author migue 1102838994
 *
 * Copyrigth: CECAR
 *
 */
package edu.cecar.componentes;

import static edu.cecar.presentacion.MenuPrincipalServidor.jTextArea1FlujoEventos;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Con esta clase se actualizan los datos en el servidor con respecto a la
 * conexión de los clientes
 *
 */
public class ActializarDatosServidor {

    private int clienteConectados;
    private int clientesDesconectados;
    private int clientesTotal;
    ArrayList<InetAddress> ipConectadas = new ArrayList<>();
    ArrayList<InetAddress> ipDesconectadas = new ArrayList<>();

    public ActializarDatosServidor() {
    }

    public int getClienteConectados() {
        return clienteConectados;
    }

    public void setClienteConectados(int clienteConectados) {
        this.clienteConectados = clienteConectados;
    }

    public int getClientesDesconectados() {
        return clientesDesconectados;
    }

    public void setClientesDesconectados(int clientesDesconectados) {
        this.clientesDesconectados = clientesDesconectados;
    }

    public int getClientesTotal() {
        return clientesTotal;
    }

    public void setClientesTotal(int clientesTotal) {
        this.clientesTotal = clientesTotal;
    }

    public ArrayList<InetAddress> getIpConectadas() {
        return ipConectadas;
    }

    public void setIpConectadas(ArrayList<InetAddress> ipConectadas) {
        this.ipConectadas = ipConectadas;
    }

    public ArrayList<InetAddress> getIpDesconectadas() {
        return ipDesconectadas;
    }

    public void setIpDesconectadas(ArrayList<InetAddress> ipDesconectadas) {
        this.ipDesconectadas = ipDesconectadas;
    }

    public void actualizarServidor() {

        jTextArea1FlujoEventos.setText("");
        jTextArea1FlujoEventos.append("          •••••••••••••••••••••••••••••"
                + "•••••••••••••\n");
        jTextArea1FlujoEventos.append("          •      Bienvenido, Servidor "
                + "Deportivo Desplegado...      • \n");
        jTextArea1FlujoEventos.append("          •••••••••••••••••••••••••••••"
                + "•••••••••••••\n");
        jTextArea1FlujoEventos.append("\n");

        jTextArea1FlujoEventos.append("**************************************"
                + "*************************\n");
        jTextArea1FlujoEventos.append("Número de clientes conectados: "
                + clienteConectados + "\n");
        jTextArea1FlujoEventos.append("Número de clientes desconectados: "
                + clientesDesconectados + "\n");
        jTextArea1FlujoEventos.append("Número total de clientes: "
                + clientesTotal + "\n");
        jTextArea1FlujoEventos.append("**************************************"
                + "*************************\n");

        jTextArea1FlujoEventos.append("======================================="
                + "==========\n");
        jTextArea1FlujoEventos.append("Historial de Ips: \n");
        jTextArea1FlujoEventos.append("IP Conectadas: \n");
        if (!ipConectadas.isEmpty()) {
            ipConectadas.forEach((ipConectada) -> {
                jTextArea1FlujoEventos.append(ipConectada + "\n");
            });
        } else {
            jTextArea1FlujoEventos.append("No hay IP Conectadas: \n");
        }

        jTextArea1FlujoEventos.append("\n");

        jTextArea1FlujoEventos.append("IP Desconectadas: \n");
        if (!ipDesconectadas.isEmpty()) {
            ipDesconectadas.forEach((ipDesconectadas) -> {
                jTextArea1FlujoEventos.append(ipDesconectadas + "\n");
            });
        } else {
            jTextArea1FlujoEventos.append("No hay IP Desconectadas: \n");
        }
        jTextArea1FlujoEventos.append("====================================="
                + "============\n");
    }

}
