package it.unibg.cs.flowchart2svg.examples;

import it.unibg.cs.flowchart2svg.Flowchart2SVG;
import it.unibg.cs.flowchart2svg.blocks.ActionBlock;
import it.unibg.cs.flowchart2svg.blocks.FinalBlock;
import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;
import it.unibg.cs.flowchart2svg.blocks.IOBlock;
import it.unibg.cs.flowchart2svg.blocks.InitialBlock;
import it.unibg.cs.flowchart2svg.links.Arrow;
import it.unibg.cs.flowchart2svg.links.Line;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;


import org.apache.batik.svggen.SVGGraphics2DIOException;


public class Example1 {

	public static void main(String args[]) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		List blocks = new LinkedList<GeneralBlock>();
		List links = new LinkedList<Line>();
		InitialBlock i1 = new InitialBlock(20,10,400,100,"void main()");
		blocks.add(i1);
		ActionBlock a1 = new ActionBlock(20,120,400,100,"int a;");
		blocks.add(a1);
		IOBlock io2 = new IOBlock(20,100,400,100,"printfsss()");
		blocks.add(io2);
		FinalBlock f1 = new FinalBlock(20,240,200,100,"end");
		blocks.add(f1);
		blocks.clear();
		Line l1 = new Line(i1.getX()+i1.getWidth()/2,i1.getY()+i1.getHeight(),a1.getX()+a1.getWidth()/2,a1.getY());
		Line l2 = new Line(a1,f1);
		Arrow arrow2 = new Arrow(80,20,100,220);
		links.add(l1);
		links.add(l2);
		links.add(arrow2);
		Flowchart2SVG.generateSVG(blocks,links,"test");
	}

}
