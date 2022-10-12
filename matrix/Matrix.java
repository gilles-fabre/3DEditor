/**
 * Base matrix handling for 3D: mul and add operations between square matrixes 
 * and vector/matrixes.
 *    
 * @date March, 2006
 * @author gilles fabre
 */
package matrix;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Point;

import trigonometry.TrigoTable;

/**
 * A class to handle basic matrix operations for 3D.
 */
public class Matrix {
	int					columns,			// matrix size, non squared are handled, but operations generate squared (min(m1.rows, m2.rows), min(m1.columns, m2.columns)) matrixes
						lines;
	protected double 	[][]values;			// the actual double values
	static final double NO_TRANSFORM_VALUES[][]={{1, 0, 0}, 
												 {0, 1, 0}, 
												 {0, 0, 1}};
	
	public static final Matrix	noTransformMatrix = new Matrix(3, 3, NO_TRANSFORM_VALUES);
	
	/**
     * Saves its own fields by calling defaultWriteObject.
     * 
     */
    private void writeObject(ObjectOutputStream out)  throws IOException {
    	out.defaultWriteObject();
 	
    	for (int i = 0; i < lines; i++) {
 	    	for (int j = 0; j < columns; j++) {
 	    		out.writeObject(new Float(values[i][j]));
 	    	}
 	    }
  }

	public Matrix() {
		// for serialization purpose...
	}
  
	// empty matrix constructor
	public Matrix(int _lines, int _columns) {
		columns = _columns;
		lines = _lines;
		values = new double[lines][columns];
	}
	
	// initialized matrix constructor
	public Matrix(int _lines, int _columns, double [][]grid) {
		columns = _columns;
		lines = _lines;
		values = new double[lines][columns];
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = grid[i][j];
			}
		}
	}

	// copy constructor
	public Matrix(Matrix matrix) {
		columns = matrix.columns;
		lines = matrix.lines;
		values = new double[lines][columns];
		for (int i = 0; i < lines; i++)
			for (int j = 0; j < columns; j++)
				values[i][j] = matrix.values[i][j];
	}
	
	/**
	 * matrix multiplication
	 * 
	 * @param m: matrix to multiply this with
	 * @return this * m
	 */ 
	public Matrix mul(Matrix m) {
		double [][]_values;
		
		_values = new double[lines][columns];

		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				_values[i][j] = 0.0;
				for (int k = 0; k < columns; k++) {
					_values[i][j] += values[i][k] * m.values[k][j];
				}
			}
		}
		
		values = _values;
		return this;
	}

	/**
	 * matrix multiplication by a double
	 * 
	 * @param d: double to multiply this with
	 * @return this * d
	 */ 
	public Matrix mul(double d) {
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] *= d;
			}
		}
		
		return this;
	}

	/**
	 * matrix subtract
	 * 
	 * @param m: matrix to sub to this
	 * @return this - m
	 */ 
	public Matrix sub(Matrix m) {
		double [][]_values;
		
		_values = new double[lines][columns];

		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
					_values[i][j] = values[i][j] - m.values[i][j];
			}
		}
		
		values = _values;
		return this;
	}

	/**
	 * matrix substract with a double
	 * 
	 * @param d: double to sub to this
	 * @return this - d
	 */ 
	public Matrix sub(double d) {
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] -= d;
			}
		}
		
		return this;
	}

	/**
	 * matrix addition
	 * 
	 * @param m: matrix to add to this
	 * @return this + m
	 */ 
	public Matrix add(Matrix m) {
		double [][]_values;
		
		_values = new double[lines][columns];

		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
					_values[i][j] = values[i][j] + m.values[i][j];
			}
		}
		
		values = _values;
		return this;
	}

	/**
	 * matrix addition with a double
	 * 
	 * @param d: double to add to this
	 * @return this + d
	 */ 
	public Matrix add(double d) {
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] += d;
			}
		}
		
		return this;
	}

	/**
	 * matrix transposition
	 * 
	 * @param none
	 * @return this where values[i][j] was changed into valyes[j][i] for all i,j in lines,columns
	 */ 
	public Matrix transpose() {
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = values[j][i];
			}
		}
		
		return this;
	}
	
	/**
	 * matrix zeroing
	 * 
	 * @param none
	 * @return this with all entries reset to 0
	 */ 
	public Matrix zero() {
		return mul(0.0);
	}

	public Matrix populate(double [][]grid) {
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = grid[i][j];
			}
		}
		
		return this;
	}


	public Matrix id()
	{
		if (lines != columns)
			return null;
		
		for (int i=0; i < lines; i++) {
			for (int j=0; j < columns; j++) {
				if (i==j)
					values[i][j]=1;
				else
					values[i][j]=0;
			}
		}
		
		return this;
	}

	public double determinant()
	{
		if (lines != columns)
			return 0;
		
		if (lines == 2)
			return values[0][0] * values[1][1]-values[0][1] * values[1][0];
		
		if (lines == 1)
			return values[0][0];
		
		Matrix 	minorIJ;
		double	detIJ;
		double 	determinant = 0;
		int sign=1;
		for (int j=0; j < columns; j++)	{
			minorIJ = minor(0, j);
			detIJ = minorIJ.determinant();
			determinant += sign * detIJ * values[0][j];
			sign = -sign;
		}
		
		return determinant;
	}

	public Matrix cofactors()
	{
		if (lines != columns)
			return null;
		
		if (lines == 1)
			return this;
		
		Matrix minorIJ;
		double detIJ;
		Matrix mResult=new Matrix(lines, columns);
		for (int i=0; i < lines; i++) {
			for (int j=0; j < columns; j++)
			{
				minorIJ = minor(i,j);
				detIJ = minorIJ.determinant();
				mResult.values[i][j]= detIJ * Math.pow(-1,i+j);
			}
		}
		return mResult;
	}

	public Matrix inverse()
	{
		double determinant = this.determinant();
		Matrix mResult = this.cofactors();
		mResult = mResult.transpose();
		mResult = mResult.mul(1/determinant);

		return mResult;
	}
	

	public Matrix minor(int iM, int jM)
	{
		if (lines != columns || columns < 2)
			return null;
		
		int i,j;
		Matrix mResult = new Matrix(lines -1, columns-1);
		for (i=0; i < iM; i++) {
			for (j=0; j < jM; j++) {
				mResult.values[i][j]= values[i][j];
			}
			for (j=jM+1; j < columns; j++) {
				mResult.values[i][j-1]= values[i][j];
			}
		}
		for (i=iM+1; i < lines; i++) {
			for (j=0; j < jM; j++) {
				mResult.values[i-1][j]= values[i][j];
			}
			for (j=jM+1; j < columns; j++)	{
				mResult.values[i-1][j-1]= values[i][j];
			}
		}
		return mResult;
	}
	
	public Matrix	rotate(int Rx, int Ry, int Rz) {
		double [][]rGrid = new double[lines][columns];

		// build the rotation matrix
		rGrid[0][0] = TrigoTable.cos(Rz) * TrigoTable.cos(Ry); 
		rGrid[1][0] = TrigoTable.sin(Rz) * TrigoTable.cos(Ry); 
		rGrid[2][0] = -TrigoTable.sin(Ry);

		rGrid[0][1] = TrigoTable.cos(Rz) * TrigoTable.sin(Ry) * TrigoTable.sin(Rx) - TrigoTable.sin(Rz) * TrigoTable.cos(Rx); 
		rGrid[1][1] = TrigoTable.sin(Rz) * TrigoTable.sin(Ry) * TrigoTable.sin(Rx) + TrigoTable.cos(Rx) * TrigoTable.cos(Rz); 
		rGrid[2][1] = TrigoTable.sin(Rx) * TrigoTable.cos(Ry);

		rGrid[0][2] = TrigoTable.cos(Rz) * TrigoTable.sin(Ry) * TrigoTable.cos(Rx) + TrigoTable.sin(Rz) * TrigoTable.sin(Rx); 
		rGrid[1][2] = TrigoTable.sin(Rz) * TrigoTable.sin(Ry) * TrigoTable.cos(Rx) - TrigoTable.cos(Rz) * TrigoTable.sin(Rx); 
		rGrid[2][2] = TrigoTable.cos(Rx) * TrigoTable.cos(Ry);

		return this.mul(new Matrix(lines, columns, rGrid));
	}

	public Matrix scale(double Sx, double Sy, double Sz) {
		double [][]sGrid = new double[lines][columns];
		
	  	// build the scaling matrix
		sGrid[0][0] = Sx;
		sGrid[1][0] = 0.0;
		sGrid[2][0] = 0.0; 

		sGrid[0][1] = 0.0; 
		sGrid[1][1] = Sy; 
		sGrid[2][1] = 0.0;

		sGrid[0][2] = 0.0; 
		sGrid[1][2] = 0.0; 
		sGrid[2][2] = Sz;

	  	return this.mul(new Matrix(lines, columns, sGrid));
	}
	
	public double[][] getValues() {
		return values;
	}
}
