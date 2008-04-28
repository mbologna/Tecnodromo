package examples;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import links.Line;

import org.apache.batik.svggen.SVGGraphics2DIOException;

import blocks.ActionBlock;
import blocks.FinalBlock;
import blocks.GeneralBlock;
import blocks.InitialBlock;
import flowchart2svg.Flowchart2SVG;

public class Example1 {

	public static void main(String args[]) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		List blocks = new LinkedList<GeneralBlock>();
		InitialBlock i1 = new InitialBlock(20,10,400,100,"void main()");
		blocks.add(i1);
		ActionBlock a1 = new ActionBlock(20,120,400,100,"int a;");
		blocks.add(a1);
		FinalBlock f1 = new FinalBlock(20,240,400,100,"end");
		blocks.add(f1);
		Line.createLink(i1.getX()+i1.getWidth()/2, i1.getY()+i1.getWidth(), a1.getX()+a1.getWidth(), a2.getY());
		Flowchart2SVG.generateSVG(blocks,"test");
	}

}
