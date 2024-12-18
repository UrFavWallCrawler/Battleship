package Game_Package;

import java.util.ArrayList;

public class Playerinfo {
	ArrayList<Ship> shipList;
	Cell[][] grid;
	boolean iWin;
	boolean isMyTurn;
	
	
	Playerinfo(){
		grid = new Cell [10][10];
		iWin = false;
		isMyTurn = false;
	}

/************************************************/
/*Methods to add ship(s)						*/
/************************************************/
		
	void addShip(Ship s) {
		shipList.addLast(s);
	}
/************************************************/
/*Methods that check if there is any ship alive	*/
/************************************************/
			
	boolean DidIWin() {
		for(int i = 0 ; i < shipList.size(); i++) {
			if(shipList.get(i).getDead()) {
				return false;
			}
		}
		return true;
	}
/************************************************/
/*Methods to change turn						*/
/************************************************/
	void ChangeTurn() {
		isMyTurn = !isMyTurn;
	}
}
