package engine;

import java.awt.event.MouseEvent;

import editor.Editor;

public class LeftViewRenderer extends Renderer {
	int		xEyeDistance = -INITIAL_EYE_DISTANCE,
			yEyeDistance = 0,
			zEyeDistance = 0;

	public LeftViewRenderer() {
		name = "Left View";
		mouseTip = "left/right mouse button translates/rotates on y and z, left + shift/ctrl scales";
	}

	public void mouseDragged(MouseEvent arg0) {
		if (mouseDown)
		{
			int x = arg0.getX(),
				y = arg0.getY();
			
			if (arg0.isShiftDown()) {
				scaleModels(1, 
						 	1 - ((y - yAnchor) * 0.1),
							1 - ((xAnchor - x) * 0.1));
			}
			else {
				if (arg0.isControlDown()) 
					scaleModels(1 - ((xAnchor - x) * 0.1), 
								1 - ((xAnchor - x) * 0.1),
								1 - ((xAnchor - x) * 0.1));
				else {
					switch (button) {
						case MouseEvent.BUTTON1:
							translateModels(0, y - yAnchor, xAnchor - x);
							break;
		
						case MouseEvent.BUTTON3: 
							rotateModels(0,
									     xAnchor - x < 0 ? 360 + xAnchor - x : xAnchor - x,
										 yAnchor - y < 0 ? 360 + yAnchor - y : yAnchor - y);
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
