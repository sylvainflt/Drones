package drones;

public class Intruder extends Cellule implements Cell{

	public Intruder(int x, int y) {
		super(x,y);
	}
	
	public void print() {
		System.out.print("I");
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean canMove() {
		return true;
	}
}
