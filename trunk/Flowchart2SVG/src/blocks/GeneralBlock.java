package blocks;

import java.awt.Color;
import java.awt.Shape;

public abstract class GeneralBlock {

	private int x, y, width, height;
	private Color blockColor;
	private String instruction;
	private Shape shape;

	public GeneralBlock(int x, int y, int width, int height, Color blockColor, String instruction) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.blockColor = blockColor;
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

	public String getText() {
		return instruction;
	}
	

}
