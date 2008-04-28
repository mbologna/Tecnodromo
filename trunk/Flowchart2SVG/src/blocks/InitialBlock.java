package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class InitialBlock extends GeneralBlock{
	
	private static final Color initialBlockColor = Color.YELLOW;
	
	public InitialBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h, text);
		super.setBlockColor(initialBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(initialBlockColor);
		g2d.drawOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		g2d.setPaint(Color.BLACK);
		g2d.drawString(this.getInstruction(), this.getX(), this.getY());
	}	
	
}
