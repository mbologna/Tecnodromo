package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class IOBlock extends GeneralBlock{
	
	private static final Color IOBlockColor = Color.BLUE;
	
	public IOBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,text);
		super.setBlockColor(IOBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(IOBlockColor);
		g2d.drawOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		g2d.setPaint(Color.BLACK);
		g2d.drawString(this.getInstruction(), this.getX(), this.getY());
	}	
}
