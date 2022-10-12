/**
 * A Point holds the 3D coordinates in a (mathematical) vector. Points can be
 * translated/rotated and scaled along/around x,y,z. Points must be projected before they
 * can be displayed. 
 * 
 * @date March, 2006
 * @author gilles fabre
 */
package model;

import matrix.Matrix;

/**
 * A class to handle basic vector operations for 3D.
 */
public class Point extends Matrix {
	static final int X = 0, 
					 Y = 1,
					 Z = 2,
					 NUM_DIMENSIONS = 3; 
	
	double		xProjection, yProjection; 		// projected coordinates
	boolean		transformed;					// the point has been transformed
	double		Xo, Yo, Zo;						// the last projection coords.
	int 		Rx, Ry, Rz;						// the last rotation angle
	double		Sx, Sy, Sz;						// the last scaling factor
	Matrix 		sMatrix;						// last scaling matrix 
	Matrix 		rMatrix;						// last rotation matrix
	
	public Point() {
		// for serialization purpose...
		super(1, NUM_DIMENSIONS);
	}
    /**
	 * 
	 * @param x is the x coordinate of the Point
	 * @param y is the y coordinate of the Point
	 * @param z is the z coordinate of the Point
	 */
	public Point(double x, double y, double z) {
		super(1, NUM_DIMENSIONS);
		values[0][X] = x;
		values[0][Y] = y;
		values[0][Z] = z;
		transformed = true;			
	}
	
	public Point(Point p) {
		super(1, NUM_DIMENSIONS);
		values[0][X] = p.getX();
		values[0][Y] = p.getY();
		values[0][Z] = p.getZ();
		transformed = p.transformed;			
	}

	/**
	 * 
	 * @return the x position of this
	 */
	public double getX() {
		return values[0][X];
	}

	public void  setX(double x)	{
		values[0][X] = x;
	}	
	
	/**
	 * 
	 * @return the y position of this
	 */
	public double getY() {
		return values[0][Y];
	}

	public void  setY(double y)	{
		values[0][Y] = y;
	}	
	
	/**
	 * 
	 * @return the z position of this
	 */
	public double getZ() {
		return values[0][Z];
	}
	
	public void  setZ(double z)	{
		values[0][Z] = z;
	}	
	
	/**
	 * 
	 * @return the x position of the Point's z projection in the 2D dimension 
	 */
	public double getZX() {
		return xProjection;
	}

	/**
	 * 
	 * @return the y position of the Point's z projection in the 2D dimension 
	 */
	public double getZY() {
		return yProjection;
	}
	
	/**
	 * Rotates this around the x, y, z axis 
	 * 
	 * @param Rx: rotation around the x axis in degrees
	 * @param Ry: rotation around the y axis in degrees
	 * @param Rz: rotation around the z axis in degrees
	 * 
	 * @return this rotated by Rx, Ry, Rz degrees.
	 */
	public Matrix	rotate(int _Rx, int _Ry, int _Rz) {
		if (rMatrix == null ||
			Rx != _Rx ||
			Ry != _Ry ||
			Rz != _Rz) {
			rMatrix = new Matrix(Matrix.noTransformMatrix);
			rMatrix.rotate(_Rx, _Ry, _Rz);
			Rx = _Rx;
			Ry = _Ry;
			Rz = _Rz;
		}
		
		transformed = true;			// the point has been transformed
		return (Point)this.mul(rMatrix);
	}

	/**
	 * Translates this by Dx, Dy, Dz units 
	 * 
	 * @param Dx: translation on the x axis
	 * @param Dy: translation on the y axis
	 * @param Dz: translation on the z axis
	 * 
	 * @return this translated by Dx, Dy, Dz units
	 */
	public Point	translate(int Dx, int Dy, int Dz) {
	  	Point delta = new Point(Dx, Dy, Dz);
		
		transformed = true;			// the point has been transformed
		return (Point)this.add(delta);
	}

	/**
	 * Scales this by Sx, Sy, Sz units 
	 * 
	 * @param Sx: scaling on the x axis
	 * @param Sy: scaling on the y axis
	 * @param Sz: scaling on the z axis
	 * 
	 * @return this scaled by Sx, Sy, Sz units
	 */
	public Matrix	scale(double _Sx, double _Sy, double _Sz) {
		
		if (sMatrix == null ||
			Sx != _Sx ||
			Sy != _Sy ||
			Sz != _Sz ) {
		  	Sx = _Sx;
		  	Sy = _Sy;
		  	Sz = _Sz;
		  	sMatrix = new Matrix(Matrix.noTransformMatrix);
		  	sMatrix.scale(Sx, Sy, Sz);
		}

		transformed = true;			// the point has been transformed
		return this.mul(sMatrix);
	}
	
	/**
	 * 
	 * @param _Xo defines the distance between the eye and the center of the screen
	 * @param _Yo defines the distance between the eye and the center of the screen
	 * @param _Zo defines the distance between the eye and the center of the screen
	 * @return this (now projected)
	 */
	public Point project(double _Xo, double _Yo, double _Zo) {
		if (transformed || Xo != _Xo || Yo != _Yo || Zo != _Zo) {
			Xo = _Xo;
			Yo = _Yo;
			Zo = _Zo;
			if (Zo == 0) {
				// special projections from top/bottom or left/right
				if (Yo == 0) {
					// from left or right...
					isoProject(Xo);
					xProjection = getX();
				}
				if (Xo == 0) {
					// from top or bottom...
					isoProject(Yo);
					yProjection = getY();
				}
			}
			
			xProjection = Xo + Zo * (getX()-Xo) / (Zo-getZ());
			yProjection = Yo + Zo * (getY()-Yo) / (Zo-getZ());
			transformed = false;
		}		
		return this;
	}

	/**
	 * Assumes the eye is on the z axis (Xo and Yo are 0)
	 * 
	 * @param _Zo defines the distance between the eye and the center of the screen
	 * @return this (now projected)
	 */
	public Point isoProject(double _Zo) {
		if (transformed || Zo != _Zo) {
			Zo = _Zo;
			xProjection = Zo * getX() / (Zo-getZ());
			yProjection = Zo * getY() / (Zo-getZ());
			transformed = false;
		}		
		return this;
	}
}
