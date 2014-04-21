import java.io.Serializable;


public class Point implements Serializable{
	private int x,y;
	public Point(int xx, int yy){
		x=xx;
		y=yy;
	}
	
	public void setX(int xx){
		x=xx;
	}
	public void setY(int yy){
		y=yy;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
