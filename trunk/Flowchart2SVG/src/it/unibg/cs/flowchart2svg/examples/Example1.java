package it.unibg.cs.flowchart2svg.examples;

import it.unibg.cs.flowchart2svg.Flowchart2SVG;
import it.unibg.cs.flowchart2svg.blocks.ActionBlock;
import it.unibg.cs.flowchart2svg.blocks.ControlBlock;
import it.unibg.cs.flowchart2svg.blocks.FinalBlock;
import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;
import it.unibg.cs.flowchart2svg.blocks.IOBlock;
import it.unibg.cs.flowchart2svg.blocks.InitialBlock;
import it.unibg.cs.flowchart2svg.labels.Label;
import it.unibg.cs.flowchart2svg.links.Arrow;
import it.unibg.cs.flowchart2svg.links.Line;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.batik.svggen.SVGGraphics2DIOException;


public class Example1 {

	public static void main(String args[]) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		/*
		 * Inizialmente si instanziano i tre contenitori fondamentali
		 */
		List<GeneralBlock> blocks = new LinkedList<GeneralBlock>();
		List<Line> links = new LinkedList<Line>();
		List<Label> labels = new LinkedList<Label>();
		/*
		 * Creo i blocchi. Specifico (x,y,w,h,testo)
		 */
		InitialBlock i1 = new InitialBlock(220,10,400,100,"void main()");
		blocks.add(i1);
		ActionBlock a1 = new ActionBlock(220,200,400,100,"int a;");
		blocks.add(a1);
		IOBlock io2 = new IOBlock(220,350,400,100,"printf(\"test a b c\");");
		blocks.add(io2);
		ControlBlock cb1 = new ControlBlock(220,500,400,100,"if (a<b)");
		blocks.add(cb1);
		FinalBlock f1 = new FinalBlock(290,650,200,100,"end");
		blocks.add(f1);
		/* Posso generare linee e frecce */
		//Line l1 = new Line(i1.getX()+i1.getWidth()/2,i1.getY()+i1.getHeight(),a1.getX()+a1.getWidth()/2,a1.getY());
		//Line l2 = new Line(a1,f1);
		//Arrow arrow2 = new Arrow(80,20,100,220);
		Arrow arrow3 = new Arrow(i1.getBottomBorder(),a1.getUpperBorder());
		links.add(arrow3);
		links.add(new Arrow(a1.getBottomBorder(),io2.getUpperBorder()));
		links.add(new Arrow(io2.getBottomBorder(),cb1.getUpperBorder()));
		links.add(new Arrow(cb1.getLeftBorder(),f1.getUpperBorder()));
		links.add(new Arrow(cb1.getRightBorder(),a1.getRightBorder()));
		/*
		 * Inserisco delle label per indicare i flussi TRUE/FALSE
		 */
		Label true1 = new Label(cb1.getRightBorder().x+20,cb1.getRightBorder().y-20, "TRUE");
		Label false1 = new Label(cb1.getLeftBorder().x-20,cb1.getRightBorder().y-20, "FALSE");
		labels.add(true1);
		labels.add(false1);
		Flowchart2SVG.generateSVG(blocks,links,labels,"test");
	}

}
