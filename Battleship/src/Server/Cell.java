package Server;

enum Status {
	  EMPTY,
	  SUNK,
	  ALIVE,
	  HIT,
	}


public class Cell {
	
	private Status status_Cell; 
	private Ship my_Ship;
	
	Cell(Ship ship){
		status_Cell = Status.EMPTY;
		my_Ship = null;
	}
/************************************************/
/*Method for setting the ship the cell belong to*/
/************************************************/
	void Set_Ship(Ship ship){
		this.my_Ship=ship;
	}
	
/************************************************/
/*Methods to change the cell's status			*/
/************************************************/
	
	void Set_Alive(){
		status_Cell = Status.ALIVE;
	}
	void Set_Hit(){
		status_Cell = Status.HIT;
	}
	void Set_Sunk(){
		status_Cell = Status.SUNK;
	}
	

/************************************************/
/*Methods to check she status of the cell		*/
/************************************************/
	
	Status Shoot(){
		if (this.status_Cell== Status.HIT) {
			return Status.HIT;
		}
		if (this.status_Cell== Status.ALIVE) {
			return Status.ALIVE;
		}
		if (this.status_Cell== Status.SUNK) {
			return Status.SUNK;
		}
		return Status.EMPTY;
	}
}

