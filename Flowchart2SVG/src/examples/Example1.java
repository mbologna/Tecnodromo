package examples;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import links.Arrow;
import links.Line;

import org.apache.batik.svggen.SVGGraphics2DIOException;

import blocks.ActionBlock;
import blocks.FinalBlock;
import blocks.GeneralBlock;
import blocks.IOBlock;
import blocks.InitialBlock;
import flowchart2svg.Flowchart2SVG;

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
