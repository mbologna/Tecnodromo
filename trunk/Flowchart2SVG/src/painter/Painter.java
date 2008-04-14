package painter;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

public class Painter {
	private List nodesList;
	
	public Painter(List nodes) {
		nodesList = nodes;
	}
	
	public void paint(Graphics2D g2d) {
		for (Iterator iterator = nodesList.iterator(); iterator.hasNext();) {
			type type = (type) iterator.next();
			
		}
	}
}
