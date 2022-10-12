package model;

public class Tetrahedron extends Cone {
	public static final int CLASS_ID = 7;
	public int getClassId() {return CLASS_ID;}

	public Tetrahedron(int diameter, int height) {
		super(diameter / 4, height, 120);
	}
	
	public Tetrahedron() {
		super(INITIAL_OBJECT_SIZE / 4, INITIAL_OBJECT_SIZE, 120);
	}

	public Model factory() {
    	Model model = new Tetrahedron();
    	return model.copy(this);
    }
}
