package model;

import java.util.Iterator;
import java.util.Vector;

import editor.Editor;

public class Transform {
	
	private Model findModel(String modelName) {
		Iterator i = Editor.editor.getModels().iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			if (m.getName() == modelName) {
				return m;
			}
		}
		
		return null;
	}
	
	public abstract class TransformAction {
		Model model;
		
		public TransformAction(String _modelName) {
			Iterator i = Editor.editor.getModels().iterator();
			while (i.hasNext()) {
				Model m = (Model)i.next();
				if (m.getName() == _modelName) {
					model = m;
					break;
				}
			}
		}

		public TransformAction() {}
		
		void apply(Model m) {
			if (model == null)
				model = m;
		}
	}
	
	public class Rotation extends TransformAction {
		Point	angle;
		
		public Rotation(Point _angle) {
			angle = new Point(_angle);
		}

		public Rotation(String _modelName, Point _angle) {
			super(_modelName);
			angle = new Point(_angle);
		}
		
		void apply(Model m) {
			super.apply(m);
			if (model != null)
				model.rotate((int)angle.getX(), (int)angle.getY(), (int)angle.getZ());
		}
	}

	public class Translation extends TransformAction {
		Point	delta;
		
		public Translation(Point _delta) {
			delta = new Point(_delta);
		}
		
		public Translation(String _modelName, Point _delta) {
			super(_modelName);
			delta = new Point(_delta);
		}
		
		void apply(Model m) {
			super.apply(m);
			if (model != null)
				model.translate((int)delta.getX(), (int)delta.getY(), (int)delta.getZ());
		}
	}

	public class Scaling extends TransformAction {
		Point	delta;
		
		public Scaling(Point _delta) {
			delta = new Point(_delta);
		}

		public Scaling(String _modelName, Point _delta) {
			super(_modelName);
			delta = new Point(_delta);
		}
		
		void apply(Model m) {
			super.apply(m);
			if (model != null)
				model.scale((int)delta.getX(), (int)delta.getY(), (int)delta.getZ());
		}
	}

	public class AddChildren extends TransformAction {
		Vector children = new Vector();
		
		public AddChildren(Vector _children) {
			children.addAll(_children);
		}
		
		public AddChildren(String _modelName, Vector _children) {
			super(_modelName);
			children.addAll(_children);
		}
		
		void apply(Model m) {
			super.apply(m);
			if (model != null) {
				Iterator c = children.iterator();
				while (c.hasNext()) {
					Model child = findModel((String)c.next());
					if (child != null)
						model.addChild(child);
				}
			}
		}
	}

	public class RemoveChildren extends TransformAction {
		Vector children = new Vector();
		
		public RemoveChildren(Vector _children) {
			children.addAll(_children);
		}
		
		public RemoveChildren(String _modelName, Vector _children) {
			super(_modelName);
			children.addAll(_children);
		}
		
		void apply(Model m) {
			super.apply(m);
			if (model != null) {
				Iterator c = children.iterator();
				while (c.hasNext()) { 
					Model child = findModel((String)c.next());
					if (child != null)
						model.removeChild(child);
				}
			}
		}
	}

	public class Composition extends TransformAction {
		Vector embedded = new Vector();
		
		public Composition(Vector _embedded) {
			embedded.addAll(_embedded);
		}

		public Composition(String _modelName, Vector _embedded) {
			super(_modelName);
			embedded.addAll(_embedded);
		}
		
		void apply(Model m) {
			super.apply(m);
			if (model != null) {
				Iterator e = embedded.iterator();
				while (e.hasNext()) { 
					Model s = findModel((String)e.next());
					if (s != null)
						model.compose(s);
				}
			}
		}
	}
}
