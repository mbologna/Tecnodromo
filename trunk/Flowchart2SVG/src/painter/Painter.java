package painter;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

import blocks.ActionBlock;
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
			if (elem instanceof ActionBlock) {
				/* crea rettangolo */
			}
			if (elem instanceof InitialBlock) {
				/* crea rettangolo smussato */
			}
			
			/* TODO */
		}
	}
}
