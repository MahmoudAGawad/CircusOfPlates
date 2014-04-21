
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Laptop extends Shape {
	
	
	public Laptop(){
		rnd = new Random();
		map = new String[]{"lap_green.png","lap_black.png","lap_blue.png"};
		int[] clr = new int[]{1,2,3};
		int random = rnd.nextInt(3);
		pic = map[random];
		color = clr[random];
		point = new Point(0, 0);
		state = new FallingState();
	}
	
	@Override
	public void draw(Graphics shape){
		try {
			shape.drawImage(ImageIO.read(new File(pic)), point.getX()-70, point.getY()-50, null);
		} catch (IOException e) {
			System.out.println("Can't load the image");
		}
	}

	@Override
	public void changeState() {
		
		if(state instanceof FallingState){
			state = new DeadState();
		}else{
			state = new FallingState();
			pic = map[rnd.nextInt(3)];
			point.setX(rnd.nextInt(1250));
			point.setY(-3 - rnd.nextInt(150));
		}
		
	}
	
	@Override
	public State getState() {
		return state;
	}
	
}
