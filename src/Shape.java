
import java.awt.Graphics;
import java.util.Random;


public abstract class Shape {
	
	protected Random rnd;
	protected String[] map; 
	protected String pic;
	protected int color;
	protected State state;
	protected Point point;
	
	
	public void setX(int x){point.setX(x);}
	public void setY(int y){point.setY(y);}
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public void setState(State state) {
		this.state = state;
	}
	public void reset(){
		state = new FallingState();
		int random = rnd.nextInt(3);
		pic = map[random];
		int[] clr = {1,2,3};
		color = clr[random];
		point.setX(rnd.nextInt(1250));
		point.setY(-3 - rnd.nextInt(150));
	}
	public abstract void draw(Graphics shape);
	public abstract void changeState();
	public abstract State getState();
	
	public int getX(){return point.getX();}
	public int getY(){return point.getY();}
	public Point getPoint(){return point;}
	public void setPoint(Point p){point=p;}
	public int getColor(){
		return color;
	}
	
	
}
