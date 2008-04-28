package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class FinalBlock extends GeneralBlock{
	
	private static final Color finalBlockColor = Color.GREEN;
	
	public FinalBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,text);
		super.setBlockColor(finalBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(finalBlockColor);
		g2d.drawOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		g2d.setPaint(Color.BLACK);
		g2d.drawString(this.getInstruction(), this.getX(), this.getY());
	}	
}