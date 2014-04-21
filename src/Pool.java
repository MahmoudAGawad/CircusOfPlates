import java.util.ArrayList;
import java.util.Random;


public class Pool {
	
	private ArrayList<Shape> available,inUse;
	private String[] random;
	private String path ;
	private LoadDynamicShape loader;
	Random rnd ;
	
	public Pool(){
		rnd = new Random();
		random=new String[]{"Plates","Laptop","Coin"};
		loader = new LoadDynamicShape();
		path = "";
		
		inUse = new ArrayList<>();
		available = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			available.add(loader.load(path, random[rnd.nextInt(3)]));
		}
		
	}
	
	public ArrayList<Shape> getInUse(){
		return inUse;
	}
	
	public Shape getObject(){
		if(available.isEmpty()) {
			if(inUse.size()>100000) return null;
			
			int sh = rnd.nextInt(3);
			Shape shp = loader.load(path, random[sh]);
			inUse.add(shp);
			return shp;
		}
		
		Shape temp = available.get(0);
		inUse.add(temp);
		available.remove(temp);
		return temp;
	}
	
	public void RealeasedObject(Shape shp){
		shp.reset();
		inUse.remove(shp);
		available.add(shp);
		
	}
	
}
