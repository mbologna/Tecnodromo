package it.unibg.cs.flowchart2svg;

import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;
import it.unibg.cs.flowchart2svg.labels.Label;
import it.unibg.cs.flowchart2svg.links.Line;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;


public class Painter {
	private List nodesList,linksList, labelsList;
	
	public Painter(List nodes,List links, List labels) {
		nodesList = nodes;
		linksList = links;
		labelsList = labels;
	}
	
	public void paintNodes(Graphics2D g2d) {
		for (Iterator iterator = nodesList.iterator(); iterator.hasNext();) {
			GeneralBlock elem = (GeneralBlock) iterator.next();
			elem.paint(g2d);
		}
	}
	
	public void paintLinks(Graphics2D g2d) {
		for (Iterator iterator = linksList.iterator(); iterator.hasNext();) {
			Line elem = (Line) iterator.next();
			elem.paint(g2d);
		}
	}
	public void paintLabels(Graphics2D g2d) {
		for (Iterator iterator = labelsList.iterator(); iterator.hasNext();) {
			Label elem = (Label) iterator.next();
			elem.paint(g2d);
		}
	}
}
