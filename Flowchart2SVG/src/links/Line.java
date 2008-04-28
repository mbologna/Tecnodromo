package links;

import java.awt.Graphics2D;

public abstract class Line {
	
	public static void createLink(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	/* link tra due id? */
}
