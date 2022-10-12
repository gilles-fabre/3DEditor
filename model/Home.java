package model;

import java.awt.Color;

import model.Connection;

public class Home extends Model {
	static final  Color HOME_COLOR = Color.GRAY;	
	static final double SIZE = 50.0;
	
	public Home() {
		super(HOME_COLOR);
		Point p1 = new Point(0, 0, -SIZE),
			  p2 = new Point(0, 0, SIZE),
			  p3 = new Point(0, -SIZE, 0),
			  p4 = new Point(0, SIZE, 0),
			  p5 = new Point(-SIZE, 0, 0),
			  p6 = new Point(SIZE, 0, 0);
	
		Connection connection = Connection.factory(this, p1);
		connection.connect(p2);
		
		connection = Connection.factory(this, p3);
		connection.connect(p4);
	
		connection = Connection.factory(this, p5);
		connection.connect(p6);
	}
	
	// don't move home !
	public void 	rotate(int Rx, int Ry, int Rz) {}
	public void		translate(int Dx, int Dy, int Dz) {}
}
