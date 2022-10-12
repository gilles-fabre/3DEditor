package engine;

import java.awt.event.MouseEvent;

import editor.Editor;

public class FrontViewRenderer extends Renderer {
	int		xEyeDistance = 0,
			yEyeDistance = 0,
			zEyeDistance = INITIAL_EYE_DISTANCE;

	public FrontViewRenderer() {
		name = "Front View";
		mouseTip = "left/right mouse button translates/rotates on x and y, left + shift/ctrl scales";
	}

	public void mouseDragged(MouseEvent arg0) {
		if (mouseDown)
		{
			int x = arg0.getX(),
				y = arg0.getY();
			
			if (arg0.isShiftDown()) {
				scaleModels(1 - ((xAnchor - x) * 0.1), 
						 	1 - ((y - yAnchor) * 0.1),
							1);
			}
			else {
				if (arg0.isControlDown()) 
					scaleModels(1 - ((xAnchor - x) * 0.1), 
								1 - ((xAnchor - x) * 0.1),
								1 - ((xAnchor - x) * 0.1));
				else { 
						switch (button) {
							case MouseEvent.BUTTON1:
								translateModels(x - xAnchor, y - yAnchor, 0);
								break;
				
							case MouseEvent.BUTTON3: 
								rotateModels(y - yAnchor < 0 ? 360 + y - yAnchor : y - yAnchor,
											 xAnchor - x < 0 ? 360 + xAnchor - x : xAnchor - x,
										 0);
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
