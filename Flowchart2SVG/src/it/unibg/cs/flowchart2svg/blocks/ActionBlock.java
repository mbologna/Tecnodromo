package it.unibg.cs.flowchart2svg.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class ActionBlock extends GeneralBlock{
	
	private static final Color actionBlockColor = new Color(255,238,204);
	
	
	public ActionBlock(int x, int y, int w, int h, String instruction) {
		super(x,y,w,h,instruction);
		super.setBlockColor(actionBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(this.getBlockColor());
		g2d.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.paint(g2d);
	}
	
}
