/**
 *
 * Clase: pdfBoxExtraerTexto
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

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 * Está clase permite leer el contenido de un archivo .pdf
 *
 */
public class PdfBoxExtraerTexto {

    private static final String RutaArchivo = "RecursosServidor/archivoPdf.pdf";
    private ArrayList<String> textoTotalGenerado = new ArrayList<String>();

    //width: 215 height: 390 aproximadamente
    public PdfBoxExtraerTexto() throws IOException {

        // Estás son las cordenadas exactas del texto que se va a extrar del
        // documento pdf descargado
        String[] izquierdaPag0 = {RutaArchivo, "0", "35", "200", "250", "590"};
        String[] derechaPag0 = {RutaArchivo, "0", "200", "100", "415", "690"};
        String[] izquierdaPag1 = {RutaArchivo, "1", "35", "35", "250", "750"};
        String[] derechaPag1 = {RutaArchivo, "1", "200", "35", "415", "780"};
        String[] izquierdaPag2 = {RutaArchivo, "2", "35", "35", "250", "780"};
        String[] derechaPag2 = {RutaArchivo, "2", "200", "30", "415", "780"};

        // Se procede a guardar dichas coordenadas en un ArrayList
        ArrayList<String[]> cordenadas = new ArrayList<String[]>();
        cordenadas.add(izquierdaPag0);
        cordenadas.add(derechaPag0);
        cordenadas.add(izquierdaPag1);
        cordenadas.add(derechaPag1);
        cordenadas.add(izquierdaPag2);
        cordenadas.add(derechaPag2);

        //Se realiza el proceso de obtener el texto
        // OJO >>> Se puede mejorar este proceso haciendo que se mantenga el 
        // documento se mantenga abierto mientras se realiza el escrapeo, pero 
        // por motivos de tiempo, se omite esta optimización<<<<<
        for (int i = 0; i < cordenadas.size(); i++) {
            // Se obtiene el texto por coordenada
            String textoObtenido
                    = obtenerRegionTextoDocument(cordenadas.get(i));
            // Se guarda texto generado
            textoTotalGenerado.add(textoObtenido);
        }

    }

    /**
     *
     * @param parametrosRegion Son las cordenas exactas de dónde va a extraer el
     * texto del PDF
     * @return String textForRegion Con el texto obtenido por región
     * @throws IOException
     */
    public static String obtenerRegionTextoDocument(String[] parametrosRegion)
            throws IOException {

        // Parameters
        String filepath = parametrosRegion[0];

        int page = Integer.parseInt(parametrosRegion[1]);
        int x = Integer.parseInt(parametrosRegion[2]);
        int y = Integer.parseInt(parametrosRegion[3]);
        int width = Integer.parseInt(parametrosRegion[4]);
        int height = Integer.parseInt(parametrosRegion[5]);

        PDDocument document = PDDocument.load(new File(filepath));

        PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
        Rectangle2D rect
                = new java.awt.geom.Rectangle2D.Float(x, y, width, height);
        textStripper.addRegion("region", rect);

        PDPage docPage = document.getPage(page);

        textStripper.extractRegions(docPage);

        String textForRegion = textStripper.getTextForRegion("region");
        document.close();

        return textForRegion;

    }

    public ArrayList<String> getTextoTotalGenerado() {
        return textoTotalGenerado;
    }
}
