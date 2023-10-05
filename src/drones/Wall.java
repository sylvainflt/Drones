package drones;

public class Wall extends Cellule implements Cell{

	public Wall(int x, int y) {
		super(x,y);
	}
	
	
	public void print() {
		System.out.print("#");
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean canMove() {
		return false;
	}
}
