public class FallingState implements State {

	@Override
	public void doAction(Shape shp) {
		int x =(int) shp.getX();
		int y =(int) shp.getY();
		Controller cont = Controller.newInstance();
		Players p1 = cont.getPlayerOne();
		Players p2 = cont.getPlayerTwo();
		int player1_x = p1.getLocationX();
 		int player1_y = p1.getLocationY();
		int player2_x = p2.getLocationX();
		int player2_y = p2.getLocationY();
		
		if (y >= cont.getView().getHeight() - 5) {
			
			shp.changeState();
			shp.getState().doAction(shp);
		}else if (Math.abs(player1_x-x)<=20&&Math.abs(player1_y-y-p1.getHeight()*20)<=10) {
			p1.addToHold(shp);
			shp.setX(player1_x);
			shp.setY(player1_y);
			p1.increamentHolds();
			cont.get_Models().remove(shp);
		} else if (Math.abs(player2_x-x)<=20&&Math.abs(player2_y-y-p2.getHeight()*20)<=10) {
			cont.getPlayerTwo().addToHold(shp);
			shp.setX(player2_x);
			shp.setY(player2_y);
			p2.increamentHolds();
			cont.get_Models().remove(shp);
		} 
		else
			shp.setY(shp.getY() + 5);
	}

}
