package drones;

public class Exit extends Cellule implements Cell{

	public Exit(int x, int y) {
		super(x,y);
	}
	
	public void print() {
		System.out.print("_");
	}
	public boolean isEmpty() {
		return true;
	}
	public boolean canMove() {
		return false;
	}
}
