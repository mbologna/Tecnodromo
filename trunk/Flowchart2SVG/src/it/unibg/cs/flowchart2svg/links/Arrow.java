package it.unibg.cs.flowchart2svg.links;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;

public class Arrow extends Line {
	private static final int LEN = 10;
	private static final double ANGLE_SX = Math.toRadians(90 + 45);
	private static final double ANGLE_DX = -ANGLE_SX;

	private MyPoint from, to;
	private MyPoint sx, dx;
	private double m;

	public Arrow(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		from = new MyPoint(x1, y1);
		to = new MyPoint(x2, y2);
		m = (y2 - y1) / (x2 - x1);

		double ang = getAngle();

		sx = new MyPoint(to, LEN, ang + ANGLE_SX);
		dx = new MyPoint(to, LEN, ang + ANGLE_DX);
	}
	
	public Arrow(Point pfrom, Point pto) {
		super((int) pfrom.x,(int) pfrom.y,(int) pto.x,(int) pto.y);
		from = new MyPoint(pfrom.x, pfrom.y);
		to = new MyPoint(pto.x, pto.y);
		m = ((double) pto.y - (double) pfrom.y) / ((double)  pto.x - (double)  pfrom.x);

		double ang = getAngle();

		sx = new MyPoint(to, LEN, ang + ANGLE_SX);
		dx = new MyPoint(to, LEN, ang + ANGLE_DX);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawLine((int) from.x, (int) from.y, (int) to.x, (int) to.y);

		g.drawLine((int) to.x, (int) to.y, (int) Math.round(sx.x), (int) Math
				.round(sx.y));
		g.drawLine((int) to.x, (int) to.y, (int) Math.round(dx.x), (int) Math
				.round(dx.y));
	}

	private double getAngle() {
		double ang = 0d;
		double w = (to.x - from.x);
		double h = (to.y - from.y);

		if ((w < 0) && (h < 0)) {
			ang = Math.atan(m) + Math.PI;
		} else if (w < 0 && h >= 0) {
			ang = Math.atan(m) + Math.PI;
		} else if (w >= 0 && h < 0) {
			ang = 2 * Math.PI + Math.atan(m);
		} else if (w >= 0 && h >= 0) {
			ang = Math.atan(m);
		}

		return ang;
	}

	private class MyPoint {
		double x, y;

		public MyPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public MyPoint(MyPoint orig, double r, double ang) {
			x = orig.x + r * Math.cos(ang);
			y = orig.y + r * Math.sin(ang);
		}

		public String toString() {
			return "[x: " + x + ", y: " + y + "]";
		}
	}

}
