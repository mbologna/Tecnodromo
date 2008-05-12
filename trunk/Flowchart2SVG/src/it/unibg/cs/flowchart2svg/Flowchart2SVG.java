package it.unibg.cs.flowchart2svg;

import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;
import it.unibg.cs.flowchart2svg.labels.Label;
import it.unibg.cs.flowchart2svg.links.Line;

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


public class Flowchart2SVG {

	public static void generateSVG(List<GeneralBlock> nodes, List<Line> links, List<Label> labels, String outputFileWithoutExtension) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		DOMImplementation domImpl = GenericDOMImplementation
				.getDOMImplementation();
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
		Painter p = new Painter(nodes,links,labels);
		p.paintNodes(svgGenerator);
		p.paintLinks(svgGenerator);
		p.paintLabels(svgGenerator);
		boolean useCSS = false;
		Writer out = new OutputStreamWriter(new FileOutputStream(new File(
				outputFileWithoutExtension + ".svg")), "UTF-8");
		svgGenerator.stream(out, useCSS);
	}
	
}
