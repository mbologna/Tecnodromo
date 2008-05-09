package it.unibg.cs.flowchart2svg.blocks;

import it.unibg.cs.flowchart2svg.shapes.Rhombus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;


public class ControlBlock extends GeneralBlock{
	
	private static final Color actionBlockColor = Color.ORANGE;
	
	public ControlBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,text);
		super.setBlockColor(actionBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(this.getBlockColor());
		g2d.fillPolygon(new Rhombus(this.getX(),this.getY(),this.getWidth(),this.getHeight()));
		super.paint(g2d);
	}	
	
	public Point getLeftBorder() {
		return new Point(this.getX()-this.getWidth()/2,this.getY()+this.getHeight()/2);
	}
	
	public Point getRightBorder() {
		return new Point(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2);
	}
}
