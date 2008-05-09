package it.unibg.cs.flowchart2svg.links;

import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;

import java.awt.Graphics2D;


public class Line {
	
	protected int x1,y1,x2,y2;
	
	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Line(GeneralBlock gb1, GeneralBlock gb2)
	{
		this.x1 = gb1.getX()+gb1.getWidth()/2;
		this.y1 = gb1.getY()+gb1.getHeight();
		this.x2 = gb2.getX()+gb2.getWidth()/2;
		this.y2 = gb2.getY();
	}

	public void paint(Graphics2D g2d) {
		g2d.drawLine(x1, y1, x2, y2);
	}
}
