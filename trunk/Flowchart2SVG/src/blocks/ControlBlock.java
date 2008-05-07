package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import shapes.Parallelogram;
import shapes.Rhombus;

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
	
}
