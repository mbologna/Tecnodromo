package it.unibg.cs.flowchart2svg.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class FinalBlock extends GeneralBlock{
	
	private static final Color finalBlockColor = Color.RED;
	
	public FinalBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,text);
		super.setBlockColor(finalBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(this.getBlockColor());
		g2d.fillRoundRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(),30,30);
		super.paint(g2d);
	}
}
