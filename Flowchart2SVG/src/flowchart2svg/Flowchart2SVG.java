/* da formalizzare bene */


/*
 * Ogni blocco generale è caratterizzato da x, y, w, h, e da un campo di testo sovraimpresso. 
 * Cosa caratterizza i vari blocchi? La forma, il colore.
 * Come si collegano i vari blocchi? Con una (o più) frecce che hanno un blocco di partenza e un blocco di arrivo.
 * 
 * Come può il programmatore produrre un diagramma SVG? 
 * Crea una serie di blocchi, non generali. Li inserisce in una lista java.util. Passa la lista a Flowchart2SVG
 * Flowchart2SVG come procede?
 * - estrae ogni singolo elemento dalla lista
 * - capisce di che tipo di blocco si tratta e genera il relativo disegno
 * - chiama il generator di batik per creare il file XML corrispondente
 */

package flowchart2svg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import painter.Painter;

public class Flowchart2SVG {
	public static void generateSVG(List nodes, String outputFileWithoutExtension) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		DOMImplementation domImpl = GenericDOMImplementation
				.getDOMImplementation();
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
		Painter p = new Painter(nodes);
		p.paint(svgGenerator);
		boolean useCSS = false;
		Writer out = new OutputStreamWriter(new FileOutputStream(new File(
				outputFileWithoutExtension + ".svg")), "UTF-8");
		svgGenerator.stream(out, useCSS);
	}
}
