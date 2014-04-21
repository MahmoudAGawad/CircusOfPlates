import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Controller {

	private Model model;
	private View view;
	private static Controller control = new Controller();
	static String url_1, url_2;
	private Players first_player, second_player;
	private ShapeFactory shape_factory;
	private Random rnd;

	private Controller() {
		view = GameFrame.panel;
		url_1 = View.url_1;
		url_2 = View.url_2;

		shape_factory = ShapeFactory.newInstance();
		model = new Model();

		rnd = new Random();

		first_player = view.getPlayerOne();
		second_player = view.getPlayerTwo();
		model.setPlayer1(first_player);
		model.setPlayer2(second_player);
		view.set();

	}

	public static Controller newInstance() {
		return control;
	}

	public ArrayList<Shape> get_Models() {
		return model.getModels();
	}

	public static Iterator obtainModelIterator() {
		return control.model.getIterator();
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void start_Game() {

		Shape obj;

		int add = 0, bound = 0;
		model.setModels(shape_factory.getInUse());
		int winner = 0;
		while (true) {
			model.setModels(shape_factory.getInUse());
			if (!view.getPause()) {
				if (first_player.getScore() == 10
						&& second_player.getScore() == 10) {
					winner = 3;
					break;
				}
				if (first_player.getScore() == 10
						|| second_player.getHold().size() > 9) {
					winner = 1;
					break;
				}
				if (second_player.getScore() == 10
						|| first_player.getHold().size() > 9) {
					winner = 2;
					break;
				}
				// view.updateView(obtainModelIterator()); //////////////////

				// view
				update();
				checkScore();
				view.repaint();
				add++;
				if (add == 8) {
					bound = rnd.nextInt(5);
					for (int i = 0; i < bound; i++) {
						obj = shape_factory.getShape();
						obj.setX(rnd.nextInt(view.getWidth() - 10));
						obj.setY(0);
					}
					add = 0;
				}
			}
			try {

				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (winner == 1) {
			view.setplayerOne_Winner();
		} else if (winner == 2) {
			view.setplayerTwo_Winner();
		} else if (winner == 3) {
			view.setplayerTwo_Winner();
		}
		view.repaint();

	}

	private void update() {
		ArrayList<Shape> shapes = model.getModels();

		for (int i = 0; i < shapes.size(); i++) {
			Shape shp = shapes.get(i);
			shp.getState().doAction(shp);
		}

		// for (Shape shp : shapes) {
		// shp.getState().doAction(shp);
		// }
	}

	private void checkScore() {
		ArrayList<Shape> holds = first_player.getHold();
		int index = -1;
		// System.out.println("Player# 1:\n");
		for (int i = 0; i < holds.size() - 2; i++) {
			// System.out.println(holds.get(i).getColor()+" "+holds.get(i+1).getColor()+" "+holds.get(i+2).getColor());
			if (holds.get(i).getColor() == holds.get(i + 1).getColor()
					&& holds.get(i + 2).getColor() == holds.get(i + 1)
							.getColor()) {
				first_player.increamentScore();
				GameFrame.player_one.setText("\t\t\tPlayer One = "
						+ first_player.getScore());
				index = i;
				break;
			}

		}

		if (index != -1) {
			int cnt = 3;
			first_player.setHeight(first_player.getHeight() - 3);
			while (cnt-- > 0) {
				shape_factory.killShape(holds.get(index));
				holds.remove(index);
			}
		}

		// System.out.println("Player# 2:\n");
		index = -1;
		holds = second_player.getHold();
		for (int i = 0; i < holds.size() - 2; i++) {
			// System.out.println(holds.get(i).getColor()+" "+holds.get(i+1).getColor()+" "+holds.get(i+2).getColor());
			if (holds.get(i).getColor() == holds.get(i + 1).getColor()
					&& holds.get(i + 2).getColor() == holds.get(i + 1)
							.getColor()) {

				second_player.increamentScore();
				GameFrame.player_two.setText("\t\t\tPlayer Two = "
						+ second_player.getScore());
				index = i;
				break;
			}

		}

		if (index != -1) {
			int cnt = 3;
			second_player.setHeight(second_player.getHeight() - 3);
			while (cnt-- > 0) {
				shape_factory.killShape(holds.get(index));
				holds.remove(index);
			}
		}

	}

}