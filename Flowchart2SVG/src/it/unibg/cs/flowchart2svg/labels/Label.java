package it.unibg.cs.flowchart2svg.labels;

import java.awt.Color;
import java.awt.Graphics2D;

public class Label {
	int x, y;
	String label;
	
	public Label(int x, int y, String label) {
		this.x = x;
		this.y = y;
		this.label = label;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(Color.BLACK);
		g2d.drawString(this.label, x,y);
	}

}
