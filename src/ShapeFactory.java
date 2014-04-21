import java.util.ArrayList;


public class ShapeFactory {
	
	private Pool pool ;
	private static ShapeFactory sf = null;
	
	private ShapeFactory(){
		pool = new Pool();
	}
	
	public Shape getShape(){
		
		return (Shape)pool.getObject();
	}
	
	public void killShape(Shape shp){
		pool.RealeasedObject(shp);
	}
	
	public static ShapeFactory newInstance(){
		if(sf==null) sf=new ShapeFactory();
		return sf;
	}
	
	public ArrayList<Shape> getInUse(){
		return pool.getInUse();
	}
	
}
