package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import shapes.Parallelogram;

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
}
