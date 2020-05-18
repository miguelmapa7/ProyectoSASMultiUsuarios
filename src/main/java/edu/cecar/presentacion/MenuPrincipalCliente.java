/**
 *
 * Clase: MenuPrincipalCliente
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
package edu.cecar.presentacion;

import edu.cecar.ApachePOI.ConectarApachePOI;
import edu.cecar.cliente.ServidorCliente;
import edu.cecar.modelo.ObjetoFTP;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Está clase crea el marco MenuPrincipalCliente
 */
public class MenuPrincipalCliente extends javax.swing.JFrame {

    // Se crea un objeto de tipo ServidorCliente
    private ServidorCliente servidorCliente;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipalCliente() throws IOException {
        initComponents();
        jButton1Resultados.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1Resultados = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1Izquierda = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2Derecha = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButton1Gestionar = new javax.swing.JButton();
        jButton1Resultados = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(0), "Resultados", 2, 0));
        jPanel1.setLayout(null);

        jTable1Resultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1Resultados);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(20, 20, 780, 30);

        jTextArea1Izquierda.setColumns(20);
        jTextArea1Izquierda.setRows(5);
        jScrollPane1.setViewportView(jTextArea1Izquierda);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 390, 280);

        jTextArea2Derecha.setColumns(20);
        jTextArea2Derecha.setRows(5);
        jScrollPane3.setViewportView(jTextArea2Derecha);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(410, 70, 390, 280);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 150, 820, 370);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(0), "Gestionar Solicitud", 2, 0));
        jPanel2.setLayout(null);

        jButton1Gestionar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButton1Gestionar.setText("Solicitar Información al Servidor");
        jButton1Gestionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1GestionarActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1Gestionar);
        jButton1Gestionar.setBounds(80, 30, 270, 40);

        jButton1Resultados.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButton1Resultados.setText("Mostrar Resultados");
        jButton1Resultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ResultadosActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1Resultados);
        jButton1Resultados.setBounds(120, 80, 190, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(200, 10, 430, 130);

        setSize(new java.awt.Dimension(860, 566));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1GestionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1GestionarActionPerformed

        jButton1Resultados.setEnabled(true);
        try {
            // Se configura para descargar el archivo .doc
            ObjetoFTP objetoFTP = new ObjetoFTP("archivo.doc");

            // Se inicializa el objeto
            servidorCliente = new ServidorCliente("127.0.0.1");

            // Se solicita el servicio el servidor 
            servidorCliente.getSocketCliente().getSalida()
                    .writeObject(objetoFTP);

            Object object;
            try {
                object = servidorCliente.getSocketCliente().getEntrada()
                        .readObject();

                // Se crea el archivo con los bytes recibidos
                crearArchivoDescargado(object);

                //JOptionPane.showMessageDialog(this, "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuPrincipalCliente.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MenuPrincipalCliente.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

            System.out.println("Archivo "
                    + objetoFTP.getNombreArchivo()
                    + " descargado");

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalCliente.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1GestionarActionPerformed

    private void jButton1ResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ResultadosActionPerformed
        // Este Botón permite leer el archivo .docx generado por el servidor
        ConectarApachePOI conectarApachePOI
                = new ConectarApachePOI();

        try {
            ArrayList<String> parrafos = new ArrayList<String>();

            parrafos = conectarApachePOI.leerArchivo();

            // Opción de respuesta 2            
            jTextArea1Izquierda.setText("");
            jTextArea1Izquierda.append(parrafos.get(0) + "\n");
            jTextArea1Izquierda.append(parrafos.get(2) + "\n");
            jTextArea1Izquierda.append(parrafos.get(4) + "\n");

            jTextArea2Derecha.setText("");
            jTextArea2Derecha.append(parrafos.get(1) + "\n");
            jTextArea2Derecha.append(parrafos.get(3) + "\n");
            jTextArea2Derecha.append(parrafos.get(5) + "\n");

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalCliente.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ResultadosActionPerformed

    /**
     *
     * @param object
     * @throws Exception
     */
    private static void crearArchivoDescargado(Object object) throws Exception {

        ObjetoFTP objetoFTP = (ObjetoFTP) object;

        FileOutputStream fileOutputStream
                = new FileOutputStream("RecursosCliente/"
                        + objetoFTP.getNombreArchivo());

        fileOutputStream.write(objetoFTP.getBytesArchivo());

        fileOutputStream.close();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Gestionar;
    private javax.swing.JButton jButton1Resultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1Resultados;
    private javax.swing.JTextArea jTextArea1Izquierda;
    private javax.swing.JTextArea jTextArea2Derecha;
    // End of variables declaration//GEN-END:variables
}
