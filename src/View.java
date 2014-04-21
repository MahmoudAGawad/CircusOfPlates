import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class View extends JPanel implements MouseMotionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Players first_player, second_player;
	private Iterator iterator = null;
	static String url_1, url_2;
	private boolean winner;
	private int _winner;
	private BufferedImage win, first_winner, second_winner, wall, pause_pic;
	private boolean pause,begin;

	public View() {
		begin = true;
		pause = false;
		url_2 = "2.png";
		url_1 = "1.png";
		addMouseMotionListener(this);
		first_player = new Players(url_1);
		second_player = new Players(url_2);
		winner = false;
		_winner = 0;
		try {
			win = ImageIO.read(new File("winner.jpg"));
			first_winner = ImageIO.read(new File("winner_1.png"));
			second_winner = ImageIO.read(new File("winner_2.png"));
			wall = ImageIO.read(new File("wall.jpg"));
			pause_pic = ImageIO.read(new File("pause.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset(){
		pause = false;
		url_2 = "2.png";
		url_1 = "1.png";
		addMouseMotionListener(this);
		winner = false;
		_winner = 0;
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		
	}

	public void setplayerOne_Winner() {
		winner = true;
		_winner = 1;
	}

	public void setplayerTwo_Winner() {
		winner = true;
		_winner = 2;
	}

	public void setTwoWinners() {
		winner = true;
		_winner = 3;
	}

	public void set() {
		// System.out.println(getWidth());
		first_player.setLocation(getWidth() / 2, getHeight() - 120);
		second_player.setLocation(getWidth() / 2, getHeight() - 130);
	}

	public void setPlayerOne(Players p) {
		first_player = p;
	}

	public void setPlayerTwo(Players p) {
		second_player = p;
	}

	public Players getPlayerOne() {
		return first_player;
	}

	public Players getPlayerTwo() {
		return second_player;
	}

	public boolean getPause() {
		return pause;
	}

	public void paintComponent(Graphics shape) {
		super.paintComponent(shape);
//		if(begin){
//			try {
//				shape.drawImage(ImageIO.read(new File("begin.jpg")), 0, 0, null);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				Thread.sleep(3000);
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			begin = false;
//			return;
//		}
		if (pause) {
			shape.drawImage(pause_pic, getWidth() / 4, 100, null);
			return;
		}

		shape.drawImage(wall, 0, 0, null);

		if (winner) {
			shape.drawImage(win, 200, 50, null);
			if (_winner == 1) {
				shape.drawImage(first_winner, 1000, 300, null);
			} else if (_winner == 2) {
				shape.drawImage(second_winner, 1000, 300, null);
			} else if (_winner == 3) {
				shape.drawImage(second_winner, 1000, 300, null);
				shape.drawImage(first_winner, 1000, 300, null);
			}
			return;
		}

		ArrayList<Shape> shapes = null;

		if (first_player != null) {
			shape.drawImage(first_player.getImage(),
					first_player.getLocationX(), first_player.getLocationY()-20,
					null);

			shape.drawLine(first_player.getLocationX(),
					first_player.getLocationY() + 20,
					first_player.getLocationX(),
					first_player.getLocationY() - 40);

			shape.drawLine(first_player.getLocationX() + 70,
					first_player.getLocationY() + 20,
					first_player.getLocationX() + 70,
					first_player.getLocationY() - 40);

			shape.drawLine(first_player.getLocationX(),
					first_player.getLocationY() - 10,
					first_player.getLocationX() + 70,
					first_player.getLocationY() - 10);

			int shift = 30;
			shapes = first_player.getHold();
			for (int i = 0; i < shapes.size(); i++) {
				Shape shp = shapes.get(i);
				shp.setY(first_player.getLocationY() - shift);
				shp.setX(first_player.getLocationX());
				shp.draw(shape);
				shift += 20;
			}

		}
		if (second_player != null) {
			shape.drawImage(second_player.getImage(),
					second_player.getLocationX(), second_player.getLocationY()-25,
					null);

			shape.drawLine(0, second_player.getLocationY() - 10, getWidth(),
					second_player.getLocationY() - 10);

			shape.drawLine(second_player.getLocationX(),
					second_player.getLocationY() + 20,
					second_player.getLocationX(),
					second_player.getLocationY() - 40);

			shape.drawLine(second_player.getLocationX() + 70,
					second_player.getLocationY() + 20,
					second_player.getLocationX() + 70,
					second_player.getLocationY() - 40);

			int shift = 40;

			shapes = second_player.getHold();
			for (int i = 0; i < shapes.size(); i++) {
				Shape shp = shapes.get(i);
				shp.setY(second_player.getLocationY() - shift);
				shp.setX(second_player.getLocationX());
				shp.draw(shape);
				shift += 20;
			}
		}

		iterator = Controller.obtainModelIterator();
		if (iterator != null) {
			while (iterator.hasNext()) {
				((Shape) iterator.next()).draw(shape);
				// repaint();
			}

		}

	}

	// Mouse Listeners //
	@Override
	public void mouseDragged(MouseEvent event) {
		first_player.setLocationX(event.getX());
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent event) {
		first_player.setLocationX(event.getX());
		repaint();
	}

	// Key Listeners //

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!pause)
				second_player.setLocationX(Math.max(
						second_player.getLocationX() - 10, 0));
			// pause = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!pause)
				second_player.setLocationX(Math.min(
						second_player.getLocationX() + 10, getWidth() - 5));
			// pause = true;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (pause)
				pause = false;
			else
				pause = true;
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
