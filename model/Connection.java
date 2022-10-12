/**
 * A connection holds a series of connected points. Connections are bijective: when a point A is
 * connected to a Point B, B gets connected to A. A connection pertains to a model, which reference 
 * must be passed upon instanciation. Connections are unique: a Point can be the origin of one 
 * and only Connection in the model. Connections automatically add themselves to the model's connections
 * when they get actually connected. Connections automatically remove themselves from the model's connections
 * when their last actual connection is removed.
 * 
 * @date April, 2006
 * @author gilles fabre
 */
package model;

import model.Point;

import java.util.Iterator;
import java.util.Vector;

import model.Model;

public class Connection {
	Point			origin;
	Vector 			connected;
	Model			model;
	
	public Connection() {
		// for serialization purpose...
	}
  
	/**
	 * 
	 * @param _model is the model where a connection from _origin will be searched
	 * @param _origin is the origin point to look for in the _model's connections
	 * @param create specifies whether a connection shall be created if none is found
	 * @return the (unique) connection for _origin in _model
	 */
	static Connection getConnection(Model _model, Point _origin, boolean create) {
		Iterator i = _model.connections.iterator();
		while (i.hasNext()) {
			Connection c = (Connection)i.next();
			if (c.origin.equals(_origin)) 
				return c;
		}
		
		return (create) ? new Connection(_model, _origin) : null;
	}
	
	/**
	 * Can be used to draw only once the vectors by drawing a connection when no 
	 * connection with a higher index exists.
	 * @param is the model where connections between p1 & p2 will be searched
	 * @param _pt1 is the point origin or end of the connection
	 * @param _pt2 is the point origin or end of the connection
	 * @return the index of the last connection between pt1 and pt2
	 */
	static public int getLastConnectionIndex(Model _model, Point _pt1, Point _pt2) {
		Iterator i = _model.connections.iterator();
		int 	 j = 0, lastFound = -1;
		while (i.hasNext()) {
			Connection c = (Connection)i.next();
			if (c.origin.equals(_pt1) && c.connected.contains(_pt2) ||
				c.origin.equals(_pt2) && c.connected.contains(_pt1))
				lastFound = j;
			
			++j;
		}
		
		return lastFound;
	}
	
	/**
	 * 
	 * @param _origin is the _origin of the bijective connection to create
	 * @param point is the point to connect to _origin
	 */
	void addBijectiveConnection(Point _origin, Point point) {
		getConnection(model, _origin, true).connect(point);
	}
	
	/**
	 * 
	 * @param _origin is the _origin of the bijective connection to remove
	 * @param point is the point to disconnect from _origin
	 */
	void removeBijectiveConnection(Point _origin, Point point) {
		Connection c = getConnection(model, _origin, false);
		if (c != null)
			c.disconnect(point);
	}
	
	/**
	 * This MUST not be called: factory ensures the uniqueness of the 
	 * Connections.
	 * 
	 * @param _model is the model associated to the newly created Connection
	 * @param _origin is the origin of the newly created Connection
	 */
	private Connection(Model _model, Point _origin) {
		connected = new Vector();
		origin = _origin;
		model = _model;
	}

	/**
	 * 
	 * @param point is the point to be connected to this ('origin)
	 */
	public void connect(Point point) {
		if (connected.isEmpty())
			model.getConnections().add(this);
		
		if (!connected.contains(point))
		{
			connected.add(point);
			addBijectiveConnection(point, this.getOrigin());
		}
	}
	
	/**
	 * 
	 * @param point is the point to be disconnected from this ('origin)
	 */
	public void disconnect(Point point) {
		if (connected.contains(point))
		{
			connected.remove(point);
			removeBijectiveConnection(point, this.getOrigin());
		}
		
		if (connected.isEmpty())
			model.getConnections().remove(this);
	}
	
	/**
	 * 
	 * @return the Vector of Points connected to this ('origin)
	 */
	public Vector getConnected() {return connected;}
	/**
	 * 
	 * @return this' origin
	 */
	public Point  getOrigin() {return origin;}
	
	/**
	 * 
	 * @param _model is the model to look through/create a Connection for... 
	 * @param _origin the origin point of the Connection
	 * @return a unique Connection from _origin in the model _model
	 */
	static public Connection factory(Model _model, Point _origin)
	{
		return getConnection(_model, _origin, true);
	}
}
