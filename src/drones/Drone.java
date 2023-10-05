package drones;

public class Drone extends Cellule implements Cell{

	public Drone(int x, int y) {
		super(x,y);
	}
	
	public void print() {
		System.out.print("D");
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean canMove() {
		return true;
	}
}
