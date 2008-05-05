package shapes;

import java.awt.Polygon;

public class Parallelogram extends Polygon {
	private int x, y, base, height, offset;
	
	public Parallelogram(int x, int y, int base, int offset, int height) {
		super();
		this.x = x;
		this.y = y;
		this.base = base;
		this.height = height;
		this.offset = offset;
		super.addPoint(x, y);
		super.addPoint(x+base,y);
		super.addPoint(x+base-offset,y+height);
		super.addPoint(x-offset, y+height);

	}
}
