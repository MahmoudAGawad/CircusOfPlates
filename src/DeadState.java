
public class DeadState implements State {

	@Override
	public void doAction(Shape shp) {
		
		ShapeFactory.newInstance().killShape(shp);
		
	}

}
