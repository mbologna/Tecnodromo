package flowchart2svg;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import blocks.ActionBlock;
import blocks.FinalBlock;
import blocks.GeneralBlock;
import blocks.InitialBlock;

public class Painter {
	private List nodesList;
	
	public Painter(List nodes) {
		nodesList = nodes;
	}
	
	public void paint(Graphics2D g2d) {
		for (Iterator iterator = nodesList.iterator(); iterator.hasNext();) {
			GeneralBlock elem = (GeneralBlock) iterator.next();
			elem.paint(g2d);
		}
	}
}
