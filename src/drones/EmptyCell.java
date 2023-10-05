package drones;

public class EmptyCell extends Cellule implements Cell{

	public EmptyCell(int x, int y) {
		super(x,y);
	}
	
	public void print() {
		System.out.print("_");
	}
	public boolean isEmpty() {
		return true;
	}
	public boolean canMove() {
		return true;
	}
}
