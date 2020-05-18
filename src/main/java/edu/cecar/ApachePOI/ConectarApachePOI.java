/**
 *
 * Clase: ConectarApachePOIError
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
package edu.cecar.ApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * Se pretende utilizar esta clase para información al usuario errores al
 * cliente
 *
 */
public class ConectarApachePOI {

    private XWPFDocument documento;

    // Constructor vacio
    public ConectarApachePOI() {
    }

    /**
     *
     * @return texto Aquí se retorna el contenido del documento
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<String> leerArchivo() throws FileNotFoundException, IOException {

        //Se crea el objeto File con la ruta del archivo
        File archivodoc = new File("RecursosCliente/archivo.doc");

        //Creamos el stream fijense bien los objetos usados
        FileInputStream fis = new FileInputStream(archivodoc);
        InputStream entradaArch = fis;

        //Se crea un documento que la POI entiende pasandole el stream
        XWPFDocument ardocx = new XWPFDocument(entradaArch);

        // Se obteniene parrafo por parrafo
        XWPFParagraph pr0;
        ArrayList<String> parrafos = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            pr0 = ardocx.getParagraphs().get(i);
            parrafos.add(pr0.getText().toString());
        }

        return parrafos;
    }

    /**
     *
     * @param textoCrearWord
     */
    public void generarArchivoDoc(ArrayList<String> textoCrearWord) {

        try {
            //Documento en blanco
            documento = new XWPFDocument();

            //Se crea un archivo con extension docx
            FileOutputStream fileOutpuStream
                    = new FileOutputStream(
                            new File("RecursosServidor/archivo.doc"));

            for (int i = 0; i < textoCrearWord.size(); i++) {

                // Se van a crear parrafos con cada región de texto escrapeado
                // del documento PDF
                XWPFParagraph parrafo = documento.createParagraph();
                parrafo.setAlignment(ParagraphAlignment.BOTH); //Se justifica el parrafo
                XWPFRun run = parrafo.createRun();
                run.setText(textoCrearWord.get(i).toString());

            }

            documento.write(fileOutpuStream);
            fileOutpuStream.close();
            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void generarErrorMensaje() {
        try {
            //Documento en blanco
            documento = new XWPFDocument();

            //Se crea un archivo con extension docx
            FileOutputStream fileOutpuStream
                    = new FileOutputStream(
                            new File("RecursosServidor/archivo.doc"));

            //En este caso se va crear un parrafo para crear un documento
            XWPFParagraph parrafo = documento.createParagraph();
            parrafo.setAlignment(ParagraphAlignment.CENTER); //Se justifica el parrafo
            XWPFRun run = parrafo.createRun();
            run.setText("error de alguna indole");

            documento.write(fileOutpuStream);
            fileOutpuStream.close();
            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XWPFDocument getDocumento() {
        return documento;
    }

}
