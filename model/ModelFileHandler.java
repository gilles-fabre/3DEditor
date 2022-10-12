/**
 * Support for the 3DEngine language:
 * 
 * 	Model := BLOCK_BEGIN ModelDescription Children? Transforms? BLOCK_END
 *  ModelDescription 	:= 	FreeDescription | 
 *  				    	BoxDescription |
 *  			 	    	DiskDescription |
 *  				    	SphereDescription |
 *  						ConeDescription |
 *  						PyramidDescription |
 *  				    	TetrahedronDescription
 *  Children			:=  BLOCK_BEGIN CHILDREN Model* BLOCK_END
 *  
 *  Transforms 			:=  BLOCK_BEGIN TRANSFORMS TransformDescription* BLOCK_END 
 *  FreeDescription 	:=	MODEL NAME Color Home Polygons
 *  BoxDescription		:=  BOX NAME Color Home Width Height Depth
 *  DiskDescription		:=  DISK NAME Color Home Radius
 *  SphereDescription	:=  SPHERE NAME Color Home Radius
 *  ConeDescription		:=  CONE NAME Color Home Radius Height
 *  PyramidDescription	:=  PYRAMID NAME Color Home Radius Height
 *  TetrahedronDescription	:= TETRAHEDRON NAME Color Home Radius Height
 *  
 *  Polygons			:=  BLOCK_BEGIN POLYGONS Points BLOCK_END
 *  Points				:=  ARRAY_BEGIN Point(',' Point)* ARRAY_END
 *  Point				:=  Triplet
 *  Color				:= 	BLOCK_BEGIN COLOR Triplet BLOCK_END
 *  Home				:=  BLOCK_BEGIN HOME Triplet BLOCK_END
 *  Angle				:= 	BLOCK_BEGIN ANGLE Triplet BLOCK_END
 *  Radius				:= 	BLOCK_BEGIN RADIUS NUMBER BLOCK_END
 *  Height				:= 	BLOCK_BEGIN HEIGHT NUMBER BLOCK_END
 *  Depth				:= 	BLOCK_BEGIN DEPTH NUMBER BLOCK_END
 *  Triplet				:=  NUMBER, NUMBER, NUMBER 
 *  
 *  ARRAY_BEGIN			:= '['
 *  ARRAY_END			:= ']'
 *  BLOCK_BEGIN			:= '{'
 *  BLOCK_END			:= '}'
 *  NUMBER				:= '0' | [1 9]([0 9])*
 *  NAME				:= [a z A Z]([a z A Z]|[0 9])* 
 *
 *  TransformDescription 	:= RotationDescription |
 *  						   TranslationDescription |
 *  						   CompositionDescription |
 *  						   ReparentDescription
 *  
 *  RotationDescription := '{' ROTATION [NAME] Coordinates '}' 
 *  TranslationDescription := '{' TRANSLATION [NAME] Coordinates '}' 
 *  CompositionDescription := '{' COMPOSITION [NAME] NAME* '}' 
 *  ReparentDescription := '{' ADD_CHILDREN [NAME] NAME* '}' |
 *   					   '{' REMOVE_CHILDREN [NAME] NAME* '}'
 *   
 *  ROTATION			:= 'Rotate'
 *  TRANSLATION			:= 'Translate'
 *  COMPOSITION			:= 'Compose'
 *  ADD_CHILDREN		:= 'Add'
 *  REMOVE_CHILDREN		:= 'Remove'
 *  
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import editor.Alert;
import editor.Editor;

public class ModelFileHandler {
	String filename = new String("untitled.model");
	
	public ModelFileHandler(String _filename) {
		filename = _filename;
	}
	
	public ModelFileHandler() {
	}

	public void parse() {
		parser modelParser;
		
		try {
			modelParser = new parser(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
    		// display an alert
			e.printStackTrace();
    		Alert alert = new Alert(Editor.editor.getEditorGUI().getMainWindow(), "couldn't instantiate the model file parser", Alert.ERROR_ALERT);
    		alert.setVisible(true);
    		return;
		}
		
		/* open input files, etc. here */
	    try {
	    		modelParser.debug_parse();
//	    		modelParser.parse();
	    } catch (Exception e) {
	      		// display an alert
	      		e.printStackTrace();
	      		Alert alert = new Alert(Editor.editor.getEditorGUI().getMainWindow(), "couldn't parse the model file", Alert.ERROR_ALERT);
	      		alert.setVisible(true);
	    }
	}

	private String buildIndentString(int indent) {
		String indentStr = new String();
		for (int i = 0; i < indent; i++)
			indentStr += " ";
		
		return indentStr;
	}
	
	private int generate(Model model, int indent, FileWriter file) throws IOException {
		String indentStr = buildIndentString(indent);
		
		// model
		file.write(indentStr + "{" + model.getClass().getSimpleName() + "\n");
		
		indent += 4;
		indentStr = buildIndentString(indent);

		file.write(indentStr + model.getName() + "\n");
		file.write(indentStr + "{Color " + model.getColor().getRed() + ", " + model.getColor().getGreen() + ", " + model.getColor().getBlue() + "}\n");
		file.write(indentStr + "{Home " + (int)model.home.getX() + ", " + (int)model.home.getY() + ", " + (int)model.home.getZ() + "}\n");
		switch (model.getClassId()) {
			case Model.CLASS_ID:
				// generate the polygons..
				file.write(indentStr + "{Polygons\n");
				indent += 4;
				indentStr = buildIndentString(indent);
				Iterator p = model.getPolygons().iterator();
				while (p.hasNext()) {
					Point points[] = (Point[])p.next();
					file.write(indentStr + "[\n");
					for (int i = 0; i < points.length; i++) {
						file.write(indentStr + "{Point " + (int)points[i].getX() + ", " + (int)points[i].getY() + ", " + (int)points[i].getZ() + "}");
						if (i < points.length - 1)
							file.write(",\n");
						else
							file.write("\n");
					}
					file.write(indentStr + "]");
					if (p.hasNext())
						file.write(",\n");
					else
						file.write("\n");
				}
				file.write(indentStr + "}\n");
				break;

			case Box.CLASS_ID:
				file.write(indentStr + "{Width " + Model.INITIAL_OBJECT_SIZE + "}\n");
				file.write(indentStr + "{Height " + Model.INITIAL_OBJECT_SIZE + "}\n");
				file.write(indentStr + "{Depth " + Model.INITIAL_OBJECT_SIZE + "}\n");
				break;
				
			case Disk.CLASS_ID:
				file.write(indentStr + "{Radius " + Model.INITIAL_OBJECT_SIZE + "}\n");
				break;
				
			case Cone.CLASS_ID:
			case Pyramid.CLASS_ID:
			case Tetrahedron.CLASS_ID:
				file.write(indentStr + "{Radius " + Model.INITIAL_OBJECT_SIZE + "}\n");
				file.write(indentStr + "{Height " + Model.INITIAL_OBJECT_SIZE + "}\n");
				break;
				
			case Cylinder.CLASS_ID:
				file.write(indentStr + "{Radius " + Model.INITIAL_OBJECT_SIZE + "}\n");
				file.write(indentStr + "{Height " + Model.INITIAL_OBJECT_SIZE + "}\n");
				break;

			case Sphere.CLASS_ID:
				file.write(indentStr + "{Radius " + Model.INITIAL_OBJECT_SIZE + "}\n");
				break;

			case Torus.CLASS_ID:
				file.write(indentStr + "{Radius " + Model.INITIAL_OBJECT_SIZE + "}\n");
				break;
		}
		
		// children, if any
		if (model.getChildren() != null) {
			file.write(indentStr + "{Children\n");
			Iterator i = model.getChildren().iterator();
			while (i.hasNext()) {
				generate((Model)i.next(), indent + 4, file);
				if (i.hasNext())
					file.write(",\n");
				else
					file.write("\n");
			}

			file.write(indentStr + "}\n");
		}
		
		// must save rotation matrix here...
		file.write(indentStr + "{TransformationMatrix \n");
		file.write(indentStr + "\t[\n");
		file.write(indentStr + "\t\t[" + model.transformationMatrix.getValues()[0][0] + ", " + model.transformationMatrix.getValues()[0][1] + ", " + model.transformationMatrix.getValues()[0][2] + "],\n");
		file.write(indentStr + "\t\t[" + model.transformationMatrix.getValues()[1][0] + ", " + model.transformationMatrix.getValues()[1][1] + ", " + model.transformationMatrix.getValues()[1][2] + "],\n");
		file.write(indentStr + "\t\t[" + model.transformationMatrix.getValues()[2][0] + ", " + model.transformationMatrix.getValues()[2][1] + ", " + model.transformationMatrix.getValues()[2][2] + "]\n");
		file.write(indentStr + "\t]\n");
		file.write(indentStr + "}\n");

		indent -= 4;
		indentStr = buildIndentString(indent);
		
		file.write(indentStr + "}");
		
		return indent;
	}
	public void generate(Model model) {
		try {
			FileWriter output = new FileWriter(model.getName() + ".model");
			generate(model, 0, output);
			output.close();
		} catch (IOException e) {
      		// display an alert
      		e.printStackTrace();
      		Alert alert = new Alert(Editor.editor.getEditorGUI().getMainWindow(), "couldn't create the model file", Alert.ERROR_ALERT);
      		alert.setVisible(true);
		}
	}
}
