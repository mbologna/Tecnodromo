package it.unibg.cs.flowchart2svg.blocks;

import it.unibg.cs.flowchart2svg.shapes.Parallelogram;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;


public class IOBlock extends GeneralBlock{	
	private static final Color IOBlockColor = Color.BLUE;
	
	public IOBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,text);
		super.setBlockColor(IOBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(this.getBlockColor());
		g2d.fillPolygon(new Parallelogram(this.getX(),this.getY(),this.getWidth(),this.getWidth()/10,this.getHeight()));
		super.paint(g2d);
	}	
	
	public Point getLeftBorder() {
		int x1 = this.getX();
		int y1 = this.getY();
		int x2 = x1-this.getWidth()/10;
		int y2 = this.getY()+this.getHeight();
		int xm = (x1+x2)/2;
		int ym = (y1+y2)/2; 
		return new Point(xm,ym);
	}
	
	public Point getRightBorder() {
		int x1 = this.getX()+ this.getWidth();
		int y1 = this.getY();
		int x2 = x1-this.getWidth()/10;
		int y2 = this.getY()+this.getHeight();
		int xm = (x1+x2)/2;
		int ym = (y1+y2)/2; 
		return new Point(xm,ym);
	}
}
