package model;

public class Pyramid extends Cone {
	public static final int CLASS_ID = 6;
	public int getClassId() {return CLASS_ID;}

	public Pyramid(int radius, int height) {
		super(radius / 4, height, 90);
	}

	public Pyramid() {
		super(INITIAL_OBJECT_SIZE / 4, INITIAL_OBJECT_SIZE, 90);
	}

	public Model factory() {
    	Model model = new Pyramid();
    	return model.copy(this);
    }
}
