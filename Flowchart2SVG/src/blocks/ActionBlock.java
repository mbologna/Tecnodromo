package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class ActionBlock extends GeneralBlock{
	
	private static final Color actionBlockColor = Color.GRAY;
	
	
	public ActionBlock(int x, int y, int w, int h, String instruction) {
		super(x,y,w,h,instruction);
		super.setBlockColor(actionBlockColor);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(actionBlockColor);
		g2d.drawRect(this.getX(), this.getY(), getWidth(), getHeight());
		g2d.setPaint(Color.BLACK);
		g2d.drawString(this.getInstruction(), this.getX(), this.getY());
	}
	
}
