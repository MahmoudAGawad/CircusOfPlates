import java.util.ArrayList;
import java.util.Iterator;

public class Model implements Container {

	static String url1, url2;
	private ArrayList<Shape> models;
	private Players player1;
	private Players player2;
	

	public Model() {
		
		models = new ArrayList<>();
		
	}

	public ArrayList<Shape> getModels() {
		return models;
	}

	public void setModels(ArrayList<Shape> models) {
		this.models = models;
	}

	public Players getPlayer1() {
		return player1;
	}

	public void setPlayer1(Players player1) {
		this.player1 = player1;
	}

	public Players getPlayer2() {
		return player2;
	}

	public void setPlayer2(Players player2) {
		this.player2 = player2;
	}
	
	
	
	
	@Override
	public ShapeIterator getIterator() {
		// TODO Auto-generated method stub
		return new ShapeIterator();
	}
	
	public void addShape(Shape shp){
		models.add(shp);
	}
	
	private class ShapeIterator implements Iterator{
		
		private int current_index;
		
		public ShapeIterator(){
			current_index=0;
		}
		
		@Override
		public boolean hasNext() {
			
			return current_index<models.size();
		}

		@Override
		public Shape next() {
			
			if(hasNext()){
				return models.get(current_index++);
			}
			
			return null;
		}

		@Override
		public void remove() {
			
		}
		
	}


	

}