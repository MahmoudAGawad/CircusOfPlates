import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class GameFrame extends JFrame {

	/**
  * 
  */
	private static final long serialVersionUID = 1L;
	static View panel;
	static GameFrame app;
	static JPanel score;
	static JTextField player_one, player_two;
	static JButton saveBtn;
	static JButton loadBtn;
	private static File file;
	private static JFileChooser fc;

	public GameFrame() {
		saveBtn = new JButton("Save");
		loadBtn = new JButton("Load");

		setLocation(0, 0);
		setSize(1300, 750);
		// this.setLayout(new GridLayout(2, 1));
		panel = new View();
		panel.setSize(getWidth(), getHeight());
		panel.setBackground(Color.WHITE);
		setLocationRelativeTo(null);

		score = new JPanel();
		score.setLayout(new GridLayout(2, 1));
		player_one = new JTextField("\t\tPlayer One = 0");
		player_two = new JTextField("\t\tPlayer Two = 0");
		// score.setSize(50, 50);
		player_one.setEditable(false);
		player_two.setEditable(false);
		score.add(player_one, BorderLayout.WEST);
		score.add(player_two, BorderLayout.EAST);
		score.add(saveBtn);
		score.add(loadBtn);

		this.add(score, BorderLayout.PAGE_END);
		this.add(panel, BorderLayout.CENTER);

		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(panel);
		fc = new JFileChooser();
	}

	public void begin(){

		app = new GameFrame();

		app.setTitle("The Game");

		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setVisible(true);

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				SnapShot sv = new SnapShot();
				ArrayList<String[]> arr = sv.save;
				sv.score_p1 = panel.getPlayerOne().getScore();
				sv.score_p2 = panel.getPlayerTwo().getScore();
				ArrayList<Shape> model = Controller.newInstance().get_Models();
				for (int i = 0; i < model.size(); i++) {
					Shape shp = model.get(i);
					String[] obj = { shp.getX() + "", shp.getY() + "",
							+shp.getColor() + "", shp.getPic(),
							shp.getClass().getName() };
					arr.add(obj);
				}

				// / hold_player_1
				model = panel.getPlayerOne().getHold();
				arr = sv.hold_1;
				for (int i = 0; i < model.size(); i++) {
					Shape shp = model.get(i);
					String[] obj = { shp.getX() + "", shp.getY() + "",
							+shp.getColor() + "", shp.getPic(),
							shp.getClass().getName() };
					arr.add(obj);
				}

				// / hold_player_2
				model = panel.getPlayerTwo().getHold();
				arr = sv.hold_2;
				for (int i = 0; i < model.size(); i++) {
					Shape shp = model.get(i);
					String[] obj = { shp.getX() + "", shp.getY() + "",
							+shp.getColor() + "", shp.getPic(),
							shp.getClass().getName() };
					arr.add(obj);
				}
				// //////////////////
				panel.reset();
				try {

					int returnVal = fc.showSaveDialog(panel);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
					}

					FileOutputStream fileOut = new FileOutputStream(file
							.getPath());

					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(sv);
					out.close();
					fileOut.close();

				} catch (Exception e) {
				}

			}
		});

		loadBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evn) {

				SnapShot e = null;
				try {
					int returnVal = fc.showOpenDialog(panel);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file2 = fc.getSelectedFile();

						FileInputStream fileIn = new FileInputStream(file2
								.getPath());

						ObjectInputStream in = new ObjectInputStream(fileIn);
						e = (SnapShot) in.readObject();
					}
					ArrayList<String[]> arr = e.save;
					LoadDynamicShape loader = new LoadDynamicShape();
					panel.getPlayerOne().setPoint(e.p1);
					panel.getPlayerTwo().setPoint(e.p2);
					panel.getPlayerTwo().setScore(e.score_p2);
					panel.getPlayerOne().setScore(e.score_p1);

					ArrayList<Shape> shapes = new ArrayList<>();
					for (int i = 0; i < arr.size(); i++) {
						String[] st = arr.get(i);
						Shape shp = loader.load("", st[4]);
						shp.setPoint(new Point(Integer.parseInt(st[0]), Integer
								.parseInt(st[1])));
						shp.setColor(Integer.parseInt(st[2]));
						shp.setPic(st[3]);
						shp.setState(new FallingState());
						shapes.add(shp);
					}
					player_one.setText("\t\tPlayer One = "
							+ panel.getPlayerOne().getScore());
					player_two.setText("\t\tPlayer Two = "
							+ panel.getPlayerTwo().getScore());
					Controller.newInstance().getModel().setModels(shapes);

					// Hold_player_1
					shapes = new ArrayList<>();
					arr = e.hold_1;
					for (int i = 0; i < arr.size(); i++) {
						String[] st = arr.get(i);
						Shape shp = loader.load("", st[4]);
						shp.setPoint(new Point(Integer.parseInt(st[0]), Integer
								.parseInt(st[1])));
						shp.setColor(Integer.parseInt(st[2]));
						shp.setPic(st[3]);
						shp.setState(new FallingState());
						shapes.add(shp);
					}
					panel.getPlayerOne().setHold(shapes);

					// Hold_player_2
					shapes = new ArrayList<>();
					arr = e.hold_2;
					for (int i = 0; i < arr.size(); i++) {
						String[] st = arr.get(i);
						Shape shp = loader.load("", st[4]);
						shp.setPoint(new Point(Integer.parseInt(st[0]), Integer
								.parseInt(st[1])));
						shp.setColor(Integer.parseInt(st[2]));
						shp.setPic(st[3]);
						shp.setState(new FallingState());
						shapes.add(shp);
					}
					panel.getPlayerTwo().setHold(shapes);
					// ////////////////
					panel.reset();
					System.out.println("Game Loaded Successfully!");
				} catch (IOException i) {
					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("Employee class not found");
					c.printStackTrace();
					return;
				}

			}
		});

		Controller.newInstance().start_Game();

	}

}