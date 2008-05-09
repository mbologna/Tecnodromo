package links;

import java.awt.Color;
import java.awt.Graphics2D;

public class Arrow extends Line {
	private static final int LEN = 10;
	private static final double ANGLE_SX = Math.toRadians(90 + 45);
	private static final double ANGLE_DX = -ANGLE_SX;

	private Point from, to;
	private Point sx, dx;
	private double m;

	public Arrow(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		from = new Point(x1, y1);
		to = new Point(x2, y2);
		m = (y2 - y1) / (x2 - x1);

		double ang = getAngle();

		sx = new Point(to, LEN, ang + ANGLE_SX);
		dx = new Point(to, LEN, ang + ANGLE_DX);
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

	private class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point(Point orig, double r, double ang) {
			x = orig.x + r * Math.cos(ang);
			y = orig.y + r * Math.sin(ang);
		}

		public String toString() {
			return "[x: " + x + ", y: " + y + "]";
		}
	}

}
