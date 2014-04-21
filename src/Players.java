import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Players {
	private BufferedImage pic = null;
	private Point point;
	private int height;
	private ArrayList<Shape> hold;
	private int score;

	public Players(String url) {
		try {
			pic = ImageIO.read(new File(url));
			point = new Point(0, 0);
			hold = new ArrayList<>();
			height = 0;
			score = 0;
		} catch (IOException e) {
			System.out.println("Image Not Found!");
		}
	}

	public void setScore(int sc) {
		score = sc;
	}

	public void increamentScore() {
		score++;
	}

	public int getScore() {
		return score;
	}

	public void setHeight(int h) {
		height = h;
	}

	public int getHeight() {
		return height + 1;
	}

	public void increamentHolds() {
		height++;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point p) {
		point = p;
	}

	public void addToHold(Shape shp) {
		hold.add(shp);
	}

	public void setHold(ArrayList<Shape> holds) {
		hold = holds;
	}

	public ArrayList<Shape> getHold() {
		return hold;
	}

	public BufferedImage getImage() {
		return pic;
	}

	public void setImage(String url) {
		try {
			pic = ImageIO.read(new File(url));
		} catch (IOException e) {
			System.out.println("Image Not Found!");
		}
	}

	public void setLocation(int x, int y) {
		point.setX(x);
		point.setY(y);
	}

	public void setLocationX(int x) {
		if (point == null)
			point = new Point(0, 0);
		point.setX(x);
	}

	public void setLocationY(int y) {
		if (point == null)
			point = new Point(0, 0);
		point.setY(y);

	}

	public int getLocationX() {
		return point.getX();
	}

	public int getLocationY() {
		return point.getY();
	}

}
