package links;

import java.awt.Graphics2D;

public class Arrow extends Line{
	public Arrow(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawLine(x1, y1, x2, y2);
	}
}
