package engine;

import java.awt.event.MouseEvent;

import editor.Editor;

public class TopViewRenderer extends Renderer {
	int		xEyeDistance = 0,
			yEyeDistance = -INITIAL_EYE_DISTANCE,
			zEyeDistance = 0;

	public TopViewRenderer() {
		name = "Top View";
		mouseTip = "left/right mouse button translates/rotates on x and z, left + shift/ctrl scales";
	}

	public void mouseDragged(MouseEvent arg0) {
		if (mouseDown)
		{
			int x = arg0.getX(),
				y = arg0.getY();
			
			if (arg0.isShiftDown()) {
				scaleModels(1 - ((xAnchor - x) * 0.1), 
						 	1,
							1 - ((y - yAnchor) * 0.1));
			}
			else { 
				if (arg0.isControlDown()) 
					scaleModels(1 - ((xAnchor - x) * 0.1), 
								1 - ((xAnchor - x) * 0.1),
								1 - ((xAnchor - x) * 0.1));
				else {
					switch (button) {
						case MouseEvent.BUTTON1:
							translateModels(x - xAnchor, 0, yAnchor - y);
							break;
			
						case MouseEvent.BUTTON3: 
							rotateModels(y - yAnchor < 0 ? 360 + y - yAnchor : y - yAnchor,
										 0,
										 x - xAnchor < 0 ? 360 + x - xAnchor : x - xAnchor);
							break;
					}
				}
			}
			
			xAnchor = x;
			yAnchor = y;
			Editor.editor.getEditorGUI().getMainWindow().repaint();
		}
	}

	protected int getXEyeDistance() {
		return xEyeDistance;
	}

	protected int getYEyeDistance() {
		return yEyeDistance;
	}

	protected int getZEyeDistance() {
		return zEyeDistance;
	}
}
