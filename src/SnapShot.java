import java.util.ArrayList;

public class SnapShot implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String[]> save;
	Point p1, p2;
	int score_p1, score_p2;
	ArrayList<String[]> hold_1,hold_2;

	public SnapShot() {
		save = new ArrayList<>();
		p1 = Controller.newInstance().getPlayerOne().getPoint();
		p2 = Controller.newInstance().getPlayerTwo().getPoint();
		hold_1 = new ArrayList<>();
		hold_2 = new ArrayList<>();
		
	}
}