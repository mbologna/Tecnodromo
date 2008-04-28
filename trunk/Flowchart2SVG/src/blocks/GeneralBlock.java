package blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GeneralBlock {

	private int x, y, width, height;
	private Color blockColor;
	private String instruction;

	public GeneralBlock(int x, int y, int width, int height, String instruction) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.instruction = instruction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getBlockColor() {
		return blockColor;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setBlockColor(Color blockColor) {
		this.blockColor = blockColor;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setPaint(Color.BLACK);
		g2d.drawString(this.getInstruction(), this.getX()+this.getWidth()/3, this.getY()+this.getHeight()/2);
	}

}
