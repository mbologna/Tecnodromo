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
		g2d.setPaint(this.getBlockColor());
		g2d.drawRoundRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(),30,30);
		super.paint(g2d);
	}	
}
