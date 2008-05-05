package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class InitialBlock extends GeneralBlock{
	
	private static final Color initialBlockColor = Color.GREEN;
	
	public InitialBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,text);
		super.setBlockColor(initialBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(this.getBlockColor());
		g2d.fillRoundRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(),30,30);
		super.paint(g2d);
	}	
	
}
