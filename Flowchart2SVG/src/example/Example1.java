package example;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.batik.svggen.SVGGraphics2DIOException;

import blocks.ActionBlock;
import blocks.FinalBlock;
import blocks.GeneralBlock;
import blocks.InitialBlock;
import flowchart2svg.Flowchart2SVG;

public class Example1 {

	public static void main(String args[]) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		List blocks = new LinkedList<GeneralBlock>();
		InitialBlock i1 = new InitialBlock(50,50,100,100,"void main()");
		blocks.add(i1);
		ActionBlock a1 = new ActionBlock(50,200,100,100,"int a;");
		blocks.add(a1);
		FinalBlock f1 = new FinalBlock(50,300,100,100,"end");
		blocks.add(f1);
		Flowchart2SVG.generateSVG(blocks,"test");
	}

}