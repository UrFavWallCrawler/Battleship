package Game_Package;

import java.util.ArrayList;



public class Ship {
	private int ID;
	private ArrayList<Cell> shipPositionList;
	private boolean isDead;
	
	Ship(ArrayList<Cell> list, int id){
		this.shipPositionList = list;
		this.ID = id;
		isDead = false;
	}
	

/************************************************/
/*Methods to check if the ship is alive 		*/
/************************************************/
	
	boolean CheckIfSunk () {
		for(int i = 0; i < shipPositionList.size(); i++) {
			if(shipPositionList.get(i).Shoot()== Status.ALIVE) {
				isDead = false;
				return false;
			}
		}
		isDead = true;
		return true;
	}
/************************************************/
/*dead getter									*/
/************************************************/
	boolean getDead() {
		return isDead;
	}
}
