package blocks;

import java.util.LinkedList;
import java.util.List;

public abstract class GeneralBlock {

	private int x, y, width, height;

	public GeneralBlock(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public List getBlockCoord() {
		List l = new LinkedList();
		l.add(x);
		l.add(y);
		l.add(width);
		l.add(height);
		return l;
	}
	
	public abstract List getBlockDescr();

}
