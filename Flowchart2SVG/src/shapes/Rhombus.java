package shapes;

import java.awt.Polygon;

@SuppressWarnings("serial")
public class Rhombus extends Polygon{
	private int x,y,diag1,diag2;
	public Rhombus(int x, int y, int diag1, int diag2) {
		super();
		this.x = x;
		this.y = y;
		this.diag1 = diag1;
		this.diag2 = diag2;
		super.addPoint(x, y);
		super.addPoint(x-diag1/2, y+diag2/2);
		super.addPoint(x, y+diag2);
		super.addPoint(x-diag1/2, y+diag2/2);
	}
}